package com.ejemplos.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
                                                    // clase producto y el tipo del id
public interface ClienteRepositorio extends JpaRepository<Cliente,String> {

}
