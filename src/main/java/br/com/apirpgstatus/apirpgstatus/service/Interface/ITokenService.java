package br.com.apirpgstatus.apirpgstatus.service.Interface;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;

public interface ITokenService {
	public String GeradorToken(Usuarios usuario);
}
