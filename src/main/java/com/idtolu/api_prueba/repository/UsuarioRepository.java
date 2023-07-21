package com.idtolu.api_prueba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.idtolu.api_prueba.model.Usuarios;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuarios, String> {

	public Optional<Usuarios> findByIdentificacion(String identificacion);

	public List<Usuarios> findByUsuario(String usuario);

}
