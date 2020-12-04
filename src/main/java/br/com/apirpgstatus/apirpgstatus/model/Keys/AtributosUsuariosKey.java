package br.com.apirpgstatus.apirpgstatus.model.Keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AtributosUsuariosKey implements Serializable {
	@Column(name="idusuario")
	public Long IdUsuario;
	
	@Column(name="idatributo")
	public Long IdAtributo;
}
