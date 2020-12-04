package br.com.apirpgstatus.apirpgstatus.model.Items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Atributos_Usuarios;

@Entity
@Table(name="Atributos")
public class Atributo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String Nome;
	
	@Transient
	private Long Valor;
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}

	public Long getValor() {
		return Valor;
	}

	public void setValor(Long valor) {
		Valor = valor;
	}
}
