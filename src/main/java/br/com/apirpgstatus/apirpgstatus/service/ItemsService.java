package br.com.apirpgstatus.apirpgstatus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirpgstatus.apirpgstatus.complementos.UsuarioComp;
import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Enum.Atributo;
import br.com.apirpgstatus.apirpgstatus.model.Enum.CodeReturn;
import br.com.apirpgstatus.apirpgstatus.model.Enum.TipoItem;
import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Atributos_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Combinacoes_Criacoes;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Consumiveis_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Criacoes_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Keys.AtributosUsuariosKey;
import br.com.apirpgstatus.apirpgstatus.model.Keys.ConsumiveisUsuariosKey;
import br.com.apirpgstatus.apirpgstatus.model.Response.ConsumirItemResponse;
import br.com.apirpgstatus.apirpgstatus.model.Response.CriarConsumivelResponse;
import br.com.apirpgstatus.apirpgstatus.model.Response.DescartarItemsResponse;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.ICombinacoes_CriacoesRepository;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IConsumiveis_UsuariosRepository;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IConsumivelRepository;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IUsuariosRepository;
import br.com.apirpgstatus.apirpgstatus.service.Interface.IItemsService;

@Service
public class ItemsService extends UsuarioComp implements IItemsService{
	
	private final int QUANTIDADE_CONSUMIVEL_CRIADO_REMOCAO = 1;
	private final int QUANTIDADE_ITEMCRIACAO_REMOVIDA = 1;
	
	@Autowired
	private IConsumivelRepository _consumivelRepository;
	@Autowired
	private ICombinacoes_CriacoesRepository _combinacoesCriacoesRepository;
	
	public ItemsService(){}
	
	@Override
	public CriarConsumivelResponse CriarConsumivel(String token, Long idItemPrimario, Long idItemSecundario) {
		Long idUsuario = Long.valueOf(this.jwtUtil.getIdFromToken(token.substring(7)));
		CriarConsumivelResponse response = new CriarConsumivelResponse(0, null);
		Consumivel itemCriado = new Consumivel();
		
		Combinacoes_Criacoes formulaCriacao = this._combinacoesCriacoesRepository.GetByIds(idItemPrimario, idItemSecundario);
		
		//VERIFICA SE A COMBINAÇÃO DE ITENS GERA ALGO
		if(formulaCriacao != null) {
			List<Criacoes_Usuarios> itemsCriacaoUtilizados = this._criacoesUsuariosRepository.VerificarItemDeCriacao(idItemPrimario, idItemSecundario, idUsuario);
			
			//VERIFICA SE O JOGADOR POSSUI OS ITENS PARA CRIAÇÃO
			if(itemsCriacaoUtilizados.size() == 2 && (itemsCriacaoUtilizados.get(0).getQuantidade() > 0 && itemsCriacaoUtilizados.get(1).getQuantidade() > 0)) {
				Consumiveis_Usuarios usuarioPossuiItem = this._usuariosConsumiveisRepository.VerifyUserHaveConsumivel(formulaCriacao.getIdItemGerado(), idUsuario);
				
				//VERIFICA SE O USUARIO POSSUI JA O ITEM REGISTRADO NO BANCO
				if(usuarioPossuiItem != null) {

					usuarioPossuiItem.setQuantidade(usuarioPossuiItem.getQuantidade() + QUANTIDADE_CONSUMIVEL_CRIADO_REMOCAO);
					
					this._usuariosConsumiveisRepository.save(usuarioPossuiItem);
					
				}else {
					ConsumiveisUsuariosKey keyConsumivel = new ConsumiveisUsuariosKey();
					keyConsumivel.idConsumivel = formulaCriacao.getIdItemGerado();
					keyConsumivel.idUsuario = idUsuario;
					
					Consumiveis_Usuarios novoItemCriado = new Consumiveis_Usuarios(keyConsumivel, QUANTIDADE_CONSUMIVEL_CRIADO_REMOCAO);
					
					this._usuariosConsumiveisRepository.save(novoItemCriado);
				}
				
				itemCriado = this._consumivelRepository.findById(formulaCriacao.getIdItemGerado()).get();
				
				response.setConsumivel(itemCriado);

				//CONSOME UM ITEM DE CRIAÇÃO DE CADA UTILIZADO PARA CRIAR O DE CONSUMO
				for(Criacoes_Usuarios itemUtilizado : itemsCriacaoUtilizados) {
					itemUtilizado.setQuantidade(itemUtilizado.getQuantidade() - QUANTIDADE_ITEMCRIACAO_REMOVIDA);
				}
				
				this._criacoesUsuariosRepository.saveAll(itemsCriacaoUtilizados);
			}else {
				response.setCodeReturn(CodeReturn.ITEMS_INSUFICIENTES_PARA_CRIACAO);
			}
			
		}else {
			response.setCodeReturn(CodeReturn.FORMULA_INVALIDA);
		}
		
		return response;
	}

	@Override
	public ConsumirItemResponse ConsumirItem(String token, Long idItem) {
		Long idUsuario = Long.valueOf(this.jwtUtil.getIdFromToken(token.substring(7)));
		ConsumirItemResponse response = new ConsumirItemResponse();
		ConsumiveisUsuariosKey key = new ConsumiveisUsuariosKey();
		key.idConsumivel = idItem;
		key.idUsuario = idUsuario;
		
		Consumiveis_Usuarios consumiveisUsuariosItem = this._usuariosConsumiveisRepository.findById(key).get();
		
		if(consumiveisUsuariosItem.getQuantidade() > 0) {
			Usuarios usuario = this.BuscarDadosUsuario(token);
			Consumivel consumivel = this._consumivelRepository.findById(idItem).get();
			
			Long valorAtualizar = this.ItemConsumidoValorAtualizar(usuario, consumivel.getIdAtributo(), consumivel.getValor());

			usuario.getAtributos().get(Integer.valueOf(consumivel.getIdAtributo().toString()) - 1).setValor(valorAtualizar);
			int index = usuario.getConsumiveis().lastIndexOf(consumivel);
			
			int quantidade = usuario.getConsumiveis().get(index).getQuantidade();
			Long atributoConsumivel = usuario.getConsumiveis().get(index).getIdAtributo();
			
			consumiveisUsuariosItem.setQuantidade(quantidade - 1);
			
			this._usuariosConsumiveisRepository.save(consumiveisUsuariosItem);
			
			AtributosUsuariosKey atributosUsuariosKey = new AtributosUsuariosKey();
			atributosUsuariosKey.IdAtributo = atributoConsumivel;
			atributosUsuariosKey.IdUsuario = idUsuario;
			
			Atributos_Usuarios atributosUsuarios = this._atributosUsuariosRepository.findById(atributosUsuariosKey).get();
			atributosUsuarios.setValor(valorAtualizar);
			this._atributosUsuariosRepository.save(atributosUsuarios);
			
			response.setCodeReturn(CodeReturn.SUCESSO);
		}else {
			response.setCodeReturn(CodeReturn.QUANTIDADE_INSUFICIENTE_CONSUMO);
		}
		
		return response;
	}

	@Override
	public DescartarItemsResponse RemoverItemDoUsuario(String token, Long idItem, int quantidade, TipoItem tipoItem) {
		DescartarItemsResponse response = new DescartarItemsResponse();
		Long idUsuario = Long.valueOf(this.jwtUtil.getIdFromToken(token.substring(7)));
		response.setCodeReturn(CodeReturn.SUCESSO);
		
		//VERIFICA O TIPO DE ITEM A SER REMOVIDO
		if(tipoItem == TipoItem.CONSUMIVEL) {	
			Consumiveis_Usuarios itemUsuario = this._usuariosConsumiveisRepository.VerifyUserHaveConsumivel(idItem, idUsuario);
			
			//VERIFICA SE O USUÁRIO POSSUI O ITEM
			if(itemUsuario != null && itemUsuario.getQuantidade() > 0) {				
				itemUsuario.setQuantidade(itemUsuario.getQuantidade() - quantidade);
				
				if(itemUsuario.getQuantidade() < 0) {
					itemUsuario.setQuantidade(0);
				}
				
				this._usuariosConsumiveisRepository.save(itemUsuario);
			}else {
				response.setCodeReturn(CodeReturn.ITEMS_INSUFICIENTES_PARA_CRIACAO);
			}
		}else {
			Criacoes_Usuarios itemUsuario = this._criacoesUsuariosRepository.VerificarItemUnicoDeCriacao(idItem, idUsuario);
			
			//VERIFICA SE O USUÁRIO POSSUI O ITEM
			if(itemUsuario.getQuantidade() > 0 && itemUsuario != null) {
				itemUsuario.setQuantidade(itemUsuario.getQuantidade() - quantidade);
				
				if(itemUsuario.getQuantidade() < 0) {
					itemUsuario.setQuantidade(0);
				}
				
				this._criacoesUsuariosRepository.save(itemUsuario);
			}else {
				response.setCodeReturn(CodeReturn.ITEMS_INSUFICIENTES_PARA_CRIACAO);
			}
		}
		
		
		return response;
	}
	
	private Long ItemConsumidoValorAtualizar(Usuarios usuario, Long atributo, Long valor) {
		Long valorRetornar = usuario.getAtributos().get(Integer.valueOf(atributo.toString()) - 1).getValor() + valor;;
		
		//SE O ITEM CONSUMIDO FOR CURAR MAIS QUE A VIDA LIMITE DO JOGADOR ELE FIXA NA VIDA MÁXIMA.
		if(atributo == Atributo.VTA) {					
			Long vidaMaxima = usuario.getAtributos().get(Integer.valueOf(Atributo.VTM.toString()) - 1).getValor();
			Long vidaAtual = usuario.getAtributos().get(Integer.valueOf(Atributo.VTA.toString()) - 1).getValor();
			
			valorRetornar = vidaAtual + valor;
			
			if(valorRetornar > vidaMaxima) {
				valorRetornar = vidaMaxima;
			}
		
		//SE O ITEM CONSUMIDOR FOR REGENERAR MAIS QUE A MANA LIMITE DO JOGADOR ELE FIXA NA MANA MÁXIMA
		}else if(atributo == Atributo.MNA) {
			Long manaMaxima = usuario.getAtributos().get(Integer.valueOf(Atributo.MNM.toString()) - 1).getValor();
			Long manaAtual = usuario.getAtributos().get(Integer.valueOf(Atributo.MNA.toString()) - 1).getValor();
			
			valorRetornar = manaAtual + valor;
			
			if(valorRetornar > manaMaxima) {
				valorRetornar = manaMaxima;
			}
			
		//SE O ITEM FOR AUMENTAR O ATRIBUTO DO JOGADOR ACIMA DO LIMITE ELE FIXA NO LIMITE
		}else if(valorRetornar > 99L) {
			valorRetornar = 99L;
		}
		
		return valorRetornar;
	}

}
