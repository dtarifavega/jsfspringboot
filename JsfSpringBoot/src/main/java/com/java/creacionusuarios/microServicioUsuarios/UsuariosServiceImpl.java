package com.java.creacionusuarios.microServicioUsuarios;

import org.springframework.stereotype.Service;

import com.java.creacionusuarios.commonServices.CommonServiceImpl;
import com.java.creacionusuarios.persistencia.Usuarios;

@Service
public class UsuariosServiceImpl extends CommonServiceImpl<Usuarios, UsuariosRepository> implements UsuariosService {

	

}
