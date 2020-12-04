package br.com.apirpgstatus.apirpgstatus.model.Keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CriacoesUsuariosKey implements Serializable{
	@Column(name="iditemcriacao")
	public Long IdItemCriacao;
	
	@Column(name="idusuario")
	public Long IdUsuario;
}
