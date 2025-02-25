package com.ejemplos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.ClienteDTO;
import com.ejemplos.DTO.ClienteDTOConverter;
import com.ejemplos.DTO.CreateClienteDTO;
import com.ejemplos.DTO.CreateCuentaDTO;
import com.ejemplos.DTO.CuentaDTO;
import com.ejemplos.DTO.CuentaDTOConverter;
import com.ejemplos.DTO.MovimientoDTO;
import com.ejemplos.DTO.MovimientoDTOConverter;
import com.ejemplos.excepciones.ApiError;
import com.ejemplos.excepciones.ClienteNotFoundException;
import com.ejemplos.excepciones.CuentaNotFoundException;
import com.ejemplos.modelo.Cliente;
import com.ejemplos.modelo.ClienteRepositorio;
import com.ejemplos.modelo.Cuenta;
import com.ejemplos.modelo.CuentaRepositorio;
import com.ejemplos.modelo.Movimiento;
import com.ejemplos.modelo.MovimientoRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el contructor
@RequestMapping("/apibanco")
public class BancoController {
	
	
	@Autowired
	private CuentaRepositorio cuentaRepositorio;
	
	@Autowired
	private CuentaDTOConverter cuentaDTOConverter;
	
	@Autowired
	private final ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private final ClienteDTOConverter clienteDTOConverter;
	
	@Autowired
	private final MovimientoRepositorio movimientoRepositorio;
	
	@Autowired
	private final MovimientoDTOConverter movimientoDTOConverter;
	
	
	@GetMapping("/movimientos")  
	public ResponseEntity<?> obtenerMov(){
		List<Movimiento>result = movimientoRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			List<MovimientoDTO> dtoList = result.stream() 
			.map(movimientoDTOConverter::convertirADTO)
			.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	
	@GetMapping("/clientes")  
	public ResponseEntity<?> obtenerClientes(){
		List<Cliente>result = clienteRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	// CRUD CLIENTE ------------------------------------
	
	@GetMapping("/verclientes")  
	public ResponseEntity<?> mostrarClientes(){
		List<Cliente>result = clienteRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			List<ClienteDTO> dtoList = result.stream() 
			.map(clienteDTOConverter::convertirADTO)
			.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> obtenerUnCliente(@PathVariable String id) {
		Cliente result = clienteRepositorio.findById(id).orElse(null); 
		
		//not found es el 404
		if(result==null)
			throw new ClienteNotFoundException(id);
			
		return ResponseEntity.ok(clienteDTOConverter.convertirADTO(result));
			
	}
	
	@PostMapping("/nuevocliente")
	public  ResponseEntity<?> nuevoCliente(@RequestBody CreateClienteDTO nuevo) {
		
		// Imprimir los valores del DTO para verificar
	    System.out.println("codCliente: " + nuevo.getCodCliente() );
	    System.out.println("apellidos: " + nuevo.getApellidos() );
	    System.out.println("direccion: " + nuevo.getDireccion() );
	    System.out.println("nombre: " + nuevo.getNombre() );
	
		Cliente saved = clienteDTOConverter.convertirACliente(nuevo);
		return  ResponseEntity.status(HttpStatus.CREATED).body( clienteRepositorio.save(saved)); //201 Created
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?>  editaCliente(@RequestBody CreateClienteDTO editar, @PathVariable String id ) {
		 System.out.println("******* ID: " + editar.getCodCliente() );
		 
		if(clienteRepositorio.existsById(id)) {
			Cliente n = clienteDTOConverter.convertirACliente(editar);			
			n.setCodCliente(id);
			
			if(editar.getApellidos() == null )
				n.setApellidos(clienteRepositorio.findById(id).get().getApellidos());
			
						
			if(editar.getDireccion() == null)
				n.setDireccion(clienteRepositorio.findById(id).get().getDireccion() );
				
			
			if(editar.getNombre() == null)
				n.setNombre(clienteRepositorio.findById(id).get().getNombre() );
				
			
			return ResponseEntity.ok(clienteRepositorio.save(n)); //ok
		}else {
			return ResponseEntity.notFound().build(); //404 no lo encuentro
		}
	
	}
	
	@DeleteMapping("/cliente/{id}") 
	public ResponseEntity<?> borrarCliente(@PathVariable String id ) {
		String saludo = "Borrado Cuenta con ID: "+id;
		if(clienteRepositorio.existsById(id) ) {
			clienteRepositorio.deleteById(id);
			return ResponseEntity.ok(saludo);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// CRUD CUENTAS ----------------------------------------------
	
	@GetMapping("/cuentasAll")  
	public ResponseEntity<?> obtenerCuentasAll(){
		List<Cuenta>result = cuentaRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	
	@GetMapping("/cuentas")  
	public ResponseEntity<?> obtenerCuentas(){
		List<Cuenta>result = cuentaRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			List<CuentaDTO> dtoList = result.stream() // muestra lo que se desea mostrar
			.map(cuentaDTOConverter::convertirADTO)
			.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}
	

	@GetMapping("/cuenta/{id}")
	public ResponseEntity<?> obtenerUnaCuenta(@PathVariable Integer id) {
		Cuenta result = cuentaRepositorio.findById(id).orElse(null); 
		
		//not found es el 404
		if(result==null)
			throw new CuentaNotFoundException(id);
			
		return ResponseEntity.ok(cuentaDTOConverter.convertirADTO(result));
			
	}
	
	/*http://localhost:8090/apibanco/cuenta
	 * 
	 {
    	"codCuenta": 121290,
    	"interes": 0.12,
    	"saldo": 12300,
    	"clienteCodCliente": "eperez",
    	"sucursalCodSucursal": 1,
    	"movimientoMovimientoPK": {
        		"codCuenta": 121221,
        		"mes": 1,
        		"numMovMes": 1
    		}
	 }
	  */
	
	@PostMapping("/cuenta")
	public  ResponseEntity<?> nuevosendero(@RequestBody CreateCuentaDTO nuevo) {
		
		// Imprimir los valores del DTO para verificar
	    System.out.println("ID: " + nuevo.getCodCuenta() );
	    System.out.println("nombre: " + nuevo.getInteres() );
	    System.out.println("distancia: " + nuevo.getSaldo() );
	    System.out.println("getMunicipioId: " + nuevo.getClienteCodCliente() );
	    System.out.println("getCodSucursal: " + nuevo.getSucursalCodSucursal() );
	    System.out.println("getMoviPK: " + nuevo.getMovimientoMovimientoPK() );
	    
		Cuenta saved = cuentaDTOConverter.convertirASendero(nuevo);
		return  ResponseEntity.status(HttpStatus.CREATED).body( cuentaRepositorio.save(saved)); //201 Created
	}
	
	
	// MODIFICA sendero | @param editar | @param id | @return  
	@PutMapping("/cuenta/{id}")
	public ResponseEntity<?>  editasendero(@RequestBody CreateCuentaDTO editar, @PathVariable Integer id ) {
		 System.out.println("******* ID: " + editar.getClienteCodCliente() );
		 
		if(cuentaRepositorio.existsById(id)) {
			Cuenta n = cuentaDTOConverter.convertirASendero(editar);			
			n.setCodCuenta(id);
			
			if(editar.getClienteCodCliente() == null )
				n.setCodCuenta(cuentaRepositorio.findById(id).get().getCodCuenta());
						
			if(editar.getInteres() == null)
				n.setInteres(cuentaRepositorio.findById(id).get().getInteres() );
			
			if(editar.getSaldo() == null)
				n.setSaldo( cuentaRepositorio.findById(id).get().getSaldo() );
			
			if(editar.getClienteCodCliente() == null)
				n.setCliente( cuentaRepositorio.findById(id).get().getCliente() );
			
			if(editar.getSucursalCodSucursal()==null)
				n.setSucursal( cuentaRepositorio.findById(id).get().getSucursal() );
			
			if(editar.getMovimientoMovimientoPK() ==null)
				n.setMovimientos( cuentaRepositorio.findById(id).get().getMovimientos() );
			
			return ResponseEntity.ok(cuentaRepositorio.save(n)); //ok
		}else {
			return ResponseEntity.notFound().build(); //404 no lo encuentro
		}
	
	}
	
	@DeleteMapping("/cuenta/{id}") 
	public ResponseEntity<?> borrarsendero2(@PathVariable Integer id ) {
		String saludo = "Borrado Cuenta con ID: "+id;
		if(cuentaRepositorio.existsById(id) ) {
			cuentaRepositorio.deleteById(id);
			return ResponseEntity.ok(saludo);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	// Excepciones
	
	//cuando se produzca un error de este tipo ejecuta este método
	@ExceptionHandler(CuentaNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(CuentaNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(ClienteNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	

	
	
	
	
	
	


}
