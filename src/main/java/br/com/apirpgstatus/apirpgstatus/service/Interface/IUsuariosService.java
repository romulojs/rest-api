package br.com.apirpgstatus.apirpgstatus.service.Interface;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Consumiveis_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Response.LoginResponse;

public interface IUsuariosService {
	public Usuarios BuscarDadosUsuario(String token);
	//public LoginResponse AutenticarUsuario(String login, String senha);
	public Usuarios getByUsername(String username);
}
