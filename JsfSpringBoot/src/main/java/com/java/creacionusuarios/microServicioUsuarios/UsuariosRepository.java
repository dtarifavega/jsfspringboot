package com.java.creacionusuarios.microServicioUsuarios;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.java.creacionusuarios.persistencia.Usuarios;



public interface UsuariosRepository extends PagingAndSortingRepository<Usuarios, Long> {
	
}
