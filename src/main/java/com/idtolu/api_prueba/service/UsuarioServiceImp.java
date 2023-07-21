package com.idtolu.api_prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idtolu.api_prueba.model.Usuarios;
import com.idtolu.api_prueba.repository.UsuarioRepository;
import com.idtolu.api_prueba.util.Crypt;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;

	@Autowired
	private Crypt encrypt;

	@Override
	public Usuarios addUsers(Usuarios user) {
		Optional<Usuarios> busquedaIdentificacion = repositorio.findByIdentificacion(user.getIdentificacion());
		List<Usuarios> busquedaUsuario = repositorio.findByUsuario(user.getUsuario());
		if (busquedaIdentificacion.isPresent() || busquedaUsuario.size() >= 1) {
			return null;
		}
		user.setClave(encrypt.passwordEnconder().encode(user.getClave()));
		return repositorio.save(user);

	}

	@Override
	public List<Usuarios> getUsers() {
		return repositorio.findAll();
	}

	@Override
	public Optional<Usuarios> getUserById(String identificacion) {
		Optional<Usuarios> consulta = repositorio.findByIdentificacion(identificacion);
		if (consulta.isPresent()) {
			return consulta;
		}
		return Optional.empty();
	}

}
