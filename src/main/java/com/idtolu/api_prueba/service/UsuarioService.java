package com.idtolu.api_prueba.service;

import java.util.List;
import java.util.Optional;

import com.idtolu.api_prueba.model.Usuarios;

public interface UsuarioService {

	public Usuarios addUsers(Usuarios user);

	public List<Usuarios> getUsers();
	
	public Optional<Usuarios> getUserById(String id);

}
