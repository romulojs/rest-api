package br.com.apirpgstatus.apirpgstatus.model.Response;

import br.com.apirpgstatus.apirpgstatus.model.Enum.CodeReturn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumirItemResponse {

	private int codeReturn;
	
	public int getCodeReturn() {
		return codeReturn;
	}

	public void setCodeReturn(int codeReturn) {
		this.codeReturn = codeReturn;
	}
	
}
