package br.com.apirpgstatus.apirpgstatus.model.Response;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;

public class LoginResponse {
	
	private int codeReturn;
	private String token;
	private Usuarios usuario;
	
	public LoginResponse(int codeReturn) {
		this.codeReturn = codeReturn;
	}

	public int getCodeReturn() {
		return codeReturn;
	}
	
	public void setCodeReturn(int codeReturn) {
		this.codeReturn = codeReturn;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
}
