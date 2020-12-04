package br.com.apirpgstatus.apirpgstatus.repository.Interface;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Consumiveis_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Keys.ConsumiveisUsuariosKey;

@Repository
@Table(name="Consumiveis_Usuarios")
public interface IConsumiveis_UsuariosRepository extends JpaRepository<Consumiveis_Usuarios, ConsumiveisUsuariosKey>{

	@Query(value="SELECT * FROM Consumiveis_Usuarios WHERE idUsuario = :usuario and idConsumivel = :consumivel", nativeQuery=true)
	public Consumiveis_Usuarios VerifyUserHaveConsumivel(@Param("consumivel") Long idConsumivel, @Param("usuario") Long idUsuario);
	
}
