package br.com.apirpgstatus.apirpgstatus.model.Request;

import java.util.List;

import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Items.Criacao;

public class ItemsRequest {

	private List<Consumivel> Consumiveis;
	private List<Criacao> Criacoes;
	
	public List<Consumivel> getConsumiveis() {
		return Consumiveis;
	}
	
	public void setConsumiveis(List<Consumivel> consumiveis) {
		Consumiveis = consumiveis;
	}
	
	public List<Criacao> getCriacoes() {
		return Criacoes;
	}
	
	public void setCriacoes(List<Criacao> criacoes) {
		Criacoes = criacoes;
	}
	
}
