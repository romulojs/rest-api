package br.com.apirpgstatus.apirpgstatus.model.Response;

import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;

public class CriarConsumivelResponse {

	private int codeReturn;
	private Consumivel consumivel;
	
	public CriarConsumivelResponse(int codeReturn, Consumivel consumivel) {
		this.codeReturn = codeReturn;
		this.consumivel = consumivel;
	}
	
	public int getCodeReturn() {
		return codeReturn;
	}
	
	public void setCodeReturn(int codeReturn) {
		this.codeReturn = codeReturn;
	}
	
	public Consumivel getConsumivel() {
		return consumivel;
	}
	
	public void setConsumivel(Consumivel consumivel) {
		this.consumivel = consumivel;
	}	
}
