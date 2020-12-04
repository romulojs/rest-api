package br.com.apirpgstatus.apirpgstatus.model.Items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Consumiveis_Usuarios;

@Entity
@Table(name="Consumiveis")
public class Consumivel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String Nome, Descricao, Img;
	private Long Valor;
	@Column(name="idatributo")
	private Long IdAtributo;	
	
	@Transient
	private int Quantidade;
	
	public Consumivel() {}
	public Consumivel(long id, String nome, String descricao, String img, Long valor, Long idAtributo) {
		Id = id;
		Nome = nome;
		Descricao = descricao;
		Img = img;
		Valor = valor;
		IdAtributo = idAtributo;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Long getValor() {
		return Valor;
	}

	public void setValor(Long valor) {
		Valor = valor;
	}

	public Long getIdAtributo() {
		return IdAtributo;
	}

	public void setIdAtributo(Long idAtributo) {
		IdAtributo = idAtributo;
	}
	
	public int getQuantidade() {
		return Quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}	
}
