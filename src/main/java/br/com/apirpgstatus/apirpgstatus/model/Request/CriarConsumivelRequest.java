package br.com.apirpgstatus.apirpgstatus.model.Request;

public class CriarConsumivelRequest {

	private Long idItemPrimario;
	private Long idItemSecundario;
	
	public Long getIdItemPrimario() {
		return idItemPrimario;
	}
	
	public void setIdItemPrimario(Long idItemPrimario) {
		this.idItemPrimario = idItemPrimario;
	}
	
	public Long getIdItemSecundario() {
		return idItemSecundario;
	}
	
	public void setIdItemSecundario(Long idItemSecundario) {
		this.idItemSecundario = idItemSecundario;
	}
	
}
