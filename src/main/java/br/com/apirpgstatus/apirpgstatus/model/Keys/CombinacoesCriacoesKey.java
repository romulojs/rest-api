package br.com.apirpgstatus.apirpgstatus.model.Keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CombinacoesCriacoesKey implements Serializable{
	@Column(name="iditemprimario")
	public Long idItemPrimario;
	
	@Column(name="iditemsecundario")
	public Long idItemSecundario;
}
