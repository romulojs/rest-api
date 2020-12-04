package br.com.apirpgstatus.apirpgstatus.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Consumiveis_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Request.LoginRequest;
import br.com.apirpgstatus.apirpgstatus.model.Response.LoginResponse;
import br.com.apirpgstatus.apirpgstatus.service.UsuariosService;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IUsuariosService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping(value="/api/Usuarios")
public class UsuariosController {

	@Autowired
	private IUsuariosService _usuariosService;
	
	@GetMapping(value="/BuscarDadosUsuario/")
	public Usuarios BuscarDadosUsuario(@RequestHeader("Authorization") String token) {
		return this._usuariosService.BuscarDadosUsuario(token);

	}
}
