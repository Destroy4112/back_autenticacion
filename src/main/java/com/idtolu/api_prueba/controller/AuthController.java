package com.idtolu.api_prueba.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idtolu.api_prueba.model.Usuarios;
import com.idtolu.api_prueba.service.AuthService;
import com.idtolu.api_prueba.util.JwtUtil;

@RestController
@RequestMapping("/api/login")
public class AuthController {

	@Autowired
	private AuthService servicio;

	@PostMapping
	public ResponseEntity<?> loguearUsuario(@RequestBody Usuarios user) {
		List<Usuarios> resultado = servicio.login(user);
		if (resultado != null) {
			String token = JwtUtil.generarToken(resultado.get(0).getUsuario(), "prueba");
			return ResponseEntity.ok(new JSONObject().put("token", token).put("usuario", resultado).toString());
		} else {
			return ResponseEntity.ok(new JSONObject().put("token", "0").toString());
		}
	}

}
