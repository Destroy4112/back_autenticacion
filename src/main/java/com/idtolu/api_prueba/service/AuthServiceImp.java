package com.idtolu.api_prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idtolu.api_prueba.model.Usuarios;
import com.idtolu.api_prueba.repository.UsuarioRepository;
import com.idtolu.api_prueba.util.Crypt;

@Service
public class AuthServiceImp implements AuthService {

	@Autowired
	private UsuarioRepository repositorio;

	@Autowired
	private Crypt encrypt;

	@Override
	public List<Usuarios> login(Usuarios user) {
		List<Usuarios> usuario = repositorio.findByUsuario(user.getUsuario());
		if (!usuario.isEmpty()) {
			boolean resultado = encrypt.passwordEnconder().matches(user.getClave(), usuario.get(0).getClave());
			if (resultado) {
				return usuario;
			} else {
				return null;
			}
		}
		return null;
	}

}
