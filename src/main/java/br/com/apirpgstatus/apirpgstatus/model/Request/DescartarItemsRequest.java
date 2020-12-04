package br.com.apirpgstatus.apirpgstatus.model.Request;

import br.com.apirpgstatus.apirpgstatus.model.Enum.TipoItem;

public class DescartarItemsRequest {

	private Long IdItem;
	private int Quantidade;
	private TipoItem TipoItem;

	public Long getIdItem() {
		return IdItem;
	}
	
	public void setIdItem(Long idItem) {
		IdItem = idItem;
	}
	
	public int getQuantidade() {
		return Quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	
	public TipoItem getTipoItem() {
		return TipoItem;
	}
	
	public void setTipoItem(TipoItem tipoItem) {
		TipoItem = tipoItem;
	}
	
}
