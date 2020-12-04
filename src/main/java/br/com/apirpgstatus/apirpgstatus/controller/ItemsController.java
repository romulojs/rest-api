package br.com.apirpgstatus.apirpgstatus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Request.ConsumirItemRequest;
import br.com.apirpgstatus.apirpgstatus.model.Request.DescartarItemsRequest;
import br.com.apirpgstatus.apirpgstatus.model.Response.ConsumirItemResponse;
import br.com.apirpgstatus.apirpgstatus.model.Response.CriarConsumivelResponse;
import br.com.apirpgstatus.apirpgstatus.model.Response.DescartarItemsResponse;
import br.com.apirpgstatus.apirpgstatus.service.ItemsService;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IItemsService;

@RestController
@RequestMapping(value="/api/Items",
	consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ItemsController {

	@Autowired
	private IItemsService _itemsService;
	
	@GetMapping(value="/CriarConsumiveis/{idItemPrimario}/{idItemSecundario}")
	public CriarConsumivelResponse CriarConsumiveis(@RequestHeader("Authorization") String token, @PathVariable(value="idItemPrimario") Long idItemPrimario, @PathVariable(value="idItemSecundario") Long idItemSecundario) {
		CriarConsumivelResponse consumivelCriado = this._itemsService.CriarConsumivel(token, idItemPrimario, idItemSecundario);
		
		return consumivelCriado;
	}
	
	@PostMapping(value="/DescartarItems")
	public DescartarItemsResponse DescartarItems(@RequestHeader("Authorization") String token, @RequestBody DescartarItemsRequest descartarItemsRequest) {
		DescartarItemsResponse response = this._itemsService.RemoverItemDoUsuario(token, descartarItemsRequest.getIdItem(), descartarItemsRequest.getQuantidade(), descartarItemsRequest.getTipoItem());
		
		return response;
	}
	
	@PostMapping(value="/ConsumirItem")
	public ConsumirItemResponse ConsumirItem(@RequestHeader("Authorization") String token, @RequestBody ConsumirItemRequest consumirItemRequest) {
		return this._itemsService.ConsumirItem(token, consumirItemRequest.getIdConsumivel());
	}
	
}
