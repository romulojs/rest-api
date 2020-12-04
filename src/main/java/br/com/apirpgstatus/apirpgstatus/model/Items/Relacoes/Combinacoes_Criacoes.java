package br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.com.apirpgstatus.apirpgstatus.model.Keys.CombinacoesCriacoesKey;

@Entity
public class Combinacoes_Criacoes {

	@EmbeddedId
	CombinacoesCriacoesKey id;
	
	@Column(name="iditemgerado")
	private Long IdItemGerado;

	public Long getIdItemGerado() {
		return IdItemGerado;
	}

	public void setIdItemGerado(Long idItemGerado) {
		IdItemGerado = idItemGerado;
	}
	
}
