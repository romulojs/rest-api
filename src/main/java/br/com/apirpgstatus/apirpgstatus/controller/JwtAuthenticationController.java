package br.com.apirpgstatus.apirpgstatus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirpgstatus.apirpgstatus.configure.JwtTokenUtil;
import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Request.JwtRequest;
import br.com.apirpgstatus.apirpgstatus.model.Response.JwtResponse;
import br.com.apirpgstatus.apirpgstatus.service.JwtUserDetailsService;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IUsuariosService;

@CrossOrigin
@RestController()
@RequestMapping(value = "/api/Auth")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private IUsuariosService _usuariosService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		Usuarios usuario = this._usuariosService.getByUsername(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(usuario);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}