package br.com.apirpgstatus.apirpgstatus.complementos;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.apirpgstatus.apirpgstatus.configure.JwtTokenUtil;
import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Atributo;
import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;
import br.com.apirpgstatus.apirpgstatus.model.Items.Criacao;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Atributos_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Consumiveis_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Criacoes_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Keys.AtributosUsuariosKey;
import br.com.apirpgstatus.apirpgstatus.model.Keys.ConsumiveisUsuariosKey;
import br.com.apirpgstatus.apirpgstatus.model.Keys.CriacoesUsuariosKey;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IAtributos_UsuariosRepository;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IConsumiveis_UsuariosRepository;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.ICriacoes_UsuariosRepository;
import br.com.apirpgstatus.apirpgstatus.repository.Interface.IUsuariosRepository;

public class UsuarioComp {
	@Autowired
	protected IUsuariosRepository _usuariosRepository;
	@Autowired
	protected IConsumiveis_UsuariosRepository _usuariosConsumiveisRepository;
	@Autowired
	protected IAtributos_UsuariosRepository _atributosUsuariosRepository;
	@Autowired
	protected ICriacoes_UsuariosRepository _criacoesUsuariosRepository;
	@Autowired
	protected JwtTokenUtil jwtUtil;
	
	public Usuarios BuscarDadosUsuario(String token) {
		Long idUsuario = Long.valueOf(this.jwtUtil.getIdFromToken(token.substring(7)));
		Usuarios usuario = this._usuariosRepository.findById(idUsuario).get();

		ConsumiveisUsuariosKey keyConsumivelUsuario = new ConsumiveisUsuariosKey();
		AtributosUsuariosKey keyAtributoUsuario = new AtributosUsuariosKey();
		CriacoesUsuariosKey keyCriacaoUsuario = new CriacoesUsuariosKey();
		
		//PEGANDO VALORES DO JOIN COM CONSUMIVEIS
		for(Consumivel consumivel : usuario.getConsumiveis()) {
			keyConsumivelUsuario.idUsuario = usuario.getId();
			keyConsumivelUsuario.idConsumivel = consumivel.getId();
			
			Consumiveis_Usuarios consumiveisUsuarios = this._usuariosConsumiveisRepository.findById(keyConsumivelUsuario).get();
			
			consumivel.setQuantidade(consumiveisUsuarios.getQuantidade());
		}
		
		//PEGANDO VALORES DO JOIN COM ATRIBUTOS
		for(Atributo atributo : usuario.getAtributos()) {
			keyAtributoUsuario.IdUsuario = usuario.getId();
			keyAtributoUsuario.IdAtributo = atributo.getId();
			
			Atributos_Usuarios atributoUsuario = this._atributosUsuariosRepository.findById(keyAtributoUsuario).get();
			
			atributo.setValor(atributoUsuario.getValor());
		}
		
		//PEGANDO VALORES DO JOIN COM CRIAÇÕES
			for(Criacao criacao : usuario.getCriacoes()) {
				keyCriacaoUsuario.IdUsuario = usuario.getId();
				keyCriacaoUsuario.IdItemCriacao = criacao.getId();
				
				Criacoes_Usuarios criacaoUsuario = this._criacoesUsuariosRepository.findById(keyCriacaoUsuario).get();
				
				criacao.setQuantidade(criacaoUsuario.getQuantidade());
			}
		
		return usuario;
	}
	
}
