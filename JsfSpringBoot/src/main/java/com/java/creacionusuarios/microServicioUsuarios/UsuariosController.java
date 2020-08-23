package com.java.creacionusuarios.microServicioUsuarios;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.creacionusuarios.common.CommonController;
import com.java.creacionusuarios.persistencia.Usuarios;

@RestController
public class UsuariosController extends CommonController<Usuarios, UsuariosService>{

	

	
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuarios usuario, @PathVariable Long id){
		Optional<Usuarios> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Usuarios usuarioDb = o.get();
		usuarioDb.setEmail(usuario.getEmail());
		usuarioDb.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioDb.setNombreApellidos(usuario.getNombreApellidos());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
	}
	
	


	
	

}
