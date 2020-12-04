package br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.IndexColumn;

import br.com.apirpgstatus.apirpgstatus.model.Keys.CriacoesUsuariosKey;

@Entity
@Table(name="Criacoes_Usuarios")
public class Criacoes_Usuarios {

	@EmbeddedId
	private CriacoesUsuariosKey Id;
	
	private int Quantidade;

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}

	public CriacoesUsuariosKey getId() {
		return Id;
	}

	public void setId(CriacoesUsuariosKey id) {
		Id = id;
	}	
}
