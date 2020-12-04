package br.com.apirpgstatus.apirpgstatus.repository.Interface;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Criacoes_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Keys.CriacoesUsuariosKey;

@Repository
@Table(name="Criacoes_Usuarios")
public interface ICriacoes_UsuariosRepository extends JpaRepository<Criacoes_Usuarios, CriacoesUsuariosKey>{

	
	@Query(value="SELECT * FROM Criacoes_Usuarios WHERE (idItemCriacao = :primario and idUsuario = :usuario) or (idItemCriacao = :secundario and idUsuario = :usuario)", nativeQuery=true)
	public List<Criacoes_Usuarios> VerificarItemDeCriacao(@Param("primario") Long idItemPrimario, @Param("secundario") Long idItemSecundario, @Param("usuario") Long idUsuario);
	
	@Query(value="SELECT * FROM Criacoes_Usuarios WHERE (idItemCriacao = :item and idUsuario = :usuario)", nativeQuery=true)
	public Criacoes_Usuarios VerificarItemUnicoDeCriacao(@Param("item") Long idItem, @Param("usuario") Long idUsuario);
	
}
