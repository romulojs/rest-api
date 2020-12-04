package br.com.apirpgstatus.apirpgstatus.repository.Interface;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Combinacoes_Criacoes;
import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Criacoes_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Keys.CombinacoesCriacoesKey;

@Repository
@Table(name="Combinacoes_Criacoes")
public interface ICombinacoes_CriacoesRepository extends JpaRepository<Combinacoes_Criacoes, CombinacoesCriacoesKey>{

	@Query(value="SELECT * FROM Combinacoes_Criacoes WHERE (idItemPrimario = :itemPrimario and idItemSecundario = :itemSecundario) || (idItemPrimario = :itemSecundario and idItemSecundario = :itemPrimario)", nativeQuery = true)
	public Combinacoes_Criacoes GetByIds(@Param("itemPrimario") Long idItemPrimario, @Param("itemSecundario") Long idItemSecundario);
	
}