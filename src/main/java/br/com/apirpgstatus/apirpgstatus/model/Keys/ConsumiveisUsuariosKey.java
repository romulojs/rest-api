package br.com.apirpgstatus.apirpgstatus.model.Keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConsumiveisUsuariosKey implements Serializable{

	@Column(name="idusuario")
	public Long idUsuario;
	
	@Column(name="idconsumivel")
	public Long idConsumivel;
	
}
