package com.idtolu.api_prueba.controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idtolu.api_prueba.model.Usuarios;
import com.idtolu.api_prueba.service.UsuarioService;
import com.idtolu.api_prueba.util.JwtUtil;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;

	@PostMapping
	public ResponseEntity<?> crearUsuario(@RequestBody Usuarios user) {
		Usuarios resultado = servicio.addUsers(user);
		if (resultado != null) {
			String token = JwtUtil.generarToken(resultado.getUsuario(), "prueba");
			return ResponseEntity.ok(new JSONObject().put("token", token).put("usuario", resultado).toString());
		} else {
			return ResponseEntity.ok(new JSONObject().put("token", "0").toString());
		}
	}

	@GetMapping("/{identificacion}")
	public ResponseEntity<?> consultarUsuario(@PathVariable String identificacion) {
		Optional<Usuarios> consulta = servicio.getUserById(identificacion);
		if (!consulta.isEmpty()) {
			return ResponseEntity.ok(consulta);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("nada");
	}
}
