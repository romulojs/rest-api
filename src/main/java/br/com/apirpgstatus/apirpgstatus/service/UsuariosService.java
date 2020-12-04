package br.com.apirpgstatus.apirpgstatus.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirpgstatus.apirpgstatus.complementos.UsuarioComp;
import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Enum.CodeReturn;
import br.com.apirpgstatus.apirpgstatus.model.Response.LoginResponse;
import br.com.apirpgstatus.apirpgstatus.service.Interface.ITokenService;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IUsuariosService;

@Service
public class UsuariosService extends UsuarioComp implements IUsuariosService{

	@Override
	public Usuarios getByUsername(String username) {
		return this._usuariosRepository.GetByUsername(username);
	}

}
