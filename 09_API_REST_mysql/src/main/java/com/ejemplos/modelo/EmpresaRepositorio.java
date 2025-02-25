package com.ejemplos.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
                                                    // clase producto y el tipo del id
public interface EmpresaRepositorio extends JpaRepository<Empresa,Long> {

}
