package br.com.apirpgstatus.apirpgstatus.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.apirpgstatus.apirpgstatus.model.Items.Atributo;
import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Items.Criacao;

@Entity
@Table(name = "usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nomeusuario")
	private String NomeUsuario;
	@Column(name="nomepersonagem")
	private String NomePersonagem;
	private int Classe;
	private String Img;
	@Column(name="levelpersonagem")
	private int LevelPersonagem;
	private String username;
	private String password;
	
	public Usuarios() {}
	public Usuarios(String nomeUsuario, String nomePersonagem, int classe, String img, int levelPersonagem) {
		NomeUsuario = nomeUsuario;
		NomePersonagem = nomePersonagem;
		Classe = classe;
		Img = img;
		LevelPersonagem = levelPersonagem;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Consumiveis_Usuarios", 
				joinColumns = { @JoinColumn(name="idusuario", referencedColumnName = "id") },
				inverseJoinColumns = { @JoinColumn(name="idconsumivel", referencedColumnName = "id") })
	private List<Consumivel> Consumiveis;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Atributos_Usuarios", 
				joinColumns = { @JoinColumn(name="idusuario", referencedColumnName = "id") },
				inverseJoinColumns = { @JoinColumn(name="idatributo", referencedColumnName = "id") })
	private List<Atributo> Atributos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Criacoes_Usuarios", 
				joinColumns = { @JoinColumn(name="idusuario", referencedColumnName = "id") },
				inverseJoinColumns = { @JoinColumn(name="iditemcriacao", referencedColumnName = "id") })
	private List<Criacao> Criacoes;
	
	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return NomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		NomeUsuario = nomeUsuario;
	}

	public String getNomePersonagem() {
		return NomePersonagem;
	}

	public void setNomePersonagem(String nomePersonagem) {
		NomePersonagem = nomePersonagem;
	}

	public int getClasse() {
		return Classe;
	}

	public void setClasse(int classe) {
		Classe = classe;
	}

	public int getLevelPersonagem() {
		return LevelPersonagem;
	}

	public void setLevelPersonagem(int levelPersonagem) {
		LevelPersonagem = levelPersonagem;
	}
	
	public List<Consumivel> getConsumiveis() {
		return Consumiveis;
	}
	
	public void setConsumiveis(List<Consumivel> consumiveis) {
		Consumiveis = consumiveis;
	}
	
	public List<Atributo> getAtributos() {
		return Atributos;
	}
	
	public void setAtributos(List<Atributo> atributos) {
		Atributos = atributos;
	}
	
	public List<Criacao> getCriacoes() {
		return Criacoes;
	}
	
	public void setCriacoes(List<Criacao> criacoes) {
		Criacoes = criacoes;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String userName) {
		this.username = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
