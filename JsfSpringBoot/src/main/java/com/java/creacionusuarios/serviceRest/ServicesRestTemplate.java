/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.creacionusuarios.serviceRest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.java.creacionusuarios.persistencia.Usuarios;

/**
 *
 * @author david
 */
@Service
public class ServicesRestTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	RestTemplate restTemplate;
	private final String urlcrearUsuario = "http://localhost:8080/creacionUsuarios/";
	private String listar = "listar";
	private String update = "update";
	private String crear = "crear";
	private String delete = "delete";

	HttpHeaders headers = new HttpHeaders();
	HttpEntity<String> entity;

	public ServicesRestTemplate() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<String>(headers);
	}

	public List<Usuarios> getUsuarios() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return restTemplate.exchange(urlcrearUsuario + listar, HttpMethod.GET, entity, List.class, params).getBody();
	}

	public void guardar(Usuarios usuario) throws Exception {
		HttpEntity<Usuarios> requestEntity = new HttpEntity<Usuarios>(usuario, headers);
		ResponseEntity<Usuarios> responseEntity = restTemplate.exchange(urlcrearUsuario + crear, HttpMethod.POST,
				requestEntity, Usuarios.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			usuario = responseEntity.getBody();
			System.out.println("user response retrieved ");
		}
	}

	public void update(Usuarios usuariopersistir) throws Exception {
		Map<String, Long> map = new HashMap<>();
		map.put("id", usuariopersistir.getId());
		HttpEntity<Usuarios> requestEntity = new HttpEntity<>(usuariopersistir, headers);
		ResponseEntity<Usuarios> responseEntity = restTemplate.exchange(urlcrearUsuario + update, HttpMethod.PUT,
				requestEntity, Usuarios.class, map);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			usuariopersistir = responseEntity.getBody();
			System.out.println("user response retrieved ");
		}
	}

	public void delete(Long id) throws Exception {

		ResponseEntity<Void> responseEntity = restTemplate.exchange(urlcrearUsuario + delete, HttpMethod.DELETE, entity,
				Void.class, id);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println("user response retrieved ");
		}
	}

}
