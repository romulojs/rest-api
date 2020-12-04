package br.com.apirpgstatus.apirpgstatus.repository.Interface;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;

@Repository
@Table(name="Usuarios")
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long>{
	@Query(value="SELECT * FROM usuarios WHERE Username = :username and Password = :password", nativeQuery=true)
	public Usuarios BuscarDadosByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query(value="SELECT * FROM usuarios WHERE Username = :username", nativeQuery=true)
	public Usuarios GetByUsername(@Param("username") String username);
}
