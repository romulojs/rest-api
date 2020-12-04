package br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.apirpgstatus.apirpgstatus.model.Keys.ConsumiveisUsuariosKey;

@Entity
@Table(name="Consumiveis_Usuarios")
public class Consumiveis_Usuarios {
	
	@EmbeddedId
	private ConsumiveisUsuariosKey Id;
	
	private int Quantidade;
	
	public Consumiveis_Usuarios() {}
	public Consumiveis_Usuarios(ConsumiveisUsuariosKey id, int quantidade) {
		this.Id = id;
		this.Quantidade = quantidade;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	
	public ConsumiveisUsuariosKey getId() {
		return Id;
	}
	
	public void setId(ConsumiveisUsuariosKey id) {
		Id = id;
	}	
}
