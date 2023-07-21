package com.idtolu.api_prueba.service;

import java.util.List;

import com.idtolu.api_prueba.model.Usuarios;

public interface AuthService {

	public List<Usuarios> login(Usuarios user);
	
}
