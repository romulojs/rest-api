package br.com.apirpgstatus.apirpgstatus.service.Interface;

import br.com.apirpgstatus.apirpgstatus.model.Enum.TipoItem;
import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Response.ConsumirItemResponse;
import br.com.apirpgstatus.apirpgstatus.model.Response.CriarConsumivelResponse;
import br.com.apirpgstatus.apirpgstatus.model.Response.DescartarItemsResponse;

public interface IItemsService {
	public CriarConsumivelResponse CriarConsumivel(String token, Long idItemPrimario, Long idItemSecundario);
	public ConsumirItemResponse ConsumirItem(String token, Long idItem);
	public DescartarItemsResponse RemoverItemDoUsuario(String token, Long idItem, int quantidade, TipoItem tipoItem);
}
