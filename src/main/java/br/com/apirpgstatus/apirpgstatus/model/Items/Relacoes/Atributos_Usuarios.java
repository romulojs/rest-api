package br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Atributo;
import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Keys.AtributosUsuariosKey;

@Entity
@Table(name="atributos_usuarios")
public class Atributos_Usuarios {

	@EmbeddedId
	private AtributosUsuariosKey Id;
	
	private Long Valor;
	
	@ManyToOne
    @MapsId("idusuario")
    @JoinColumn(name = "idusuario")
    private Usuarios Usuarios;

    @ManyToOne
    @MapsId("idatributo")
    @JoinColumn(name = "idatributo")
    private Atributo Atributos;   
	
	public AtributosUsuariosKey getId() {
		return Id;
	}

	public void setId(AtributosUsuariosKey id) {
		Id = id;
	}

	public Long getValor() {
		return Valor;
	}

	public void setValor(Long valor) {
		Valor = valor;
	}
}
