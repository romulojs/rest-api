package br.com.apirpgstatus.apirpgstatus.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IUsuariosService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private IUsuariosService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios user = userService.getByUsername(username);
		
		if (user.getUsername().equals(username)) {
			return new User(username, user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado com login: " + username);
		}
	}
}