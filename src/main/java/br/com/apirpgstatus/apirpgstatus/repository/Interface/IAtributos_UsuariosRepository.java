package br.com.apirpgstatus.apirpgstatus.repository.Interface;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirpgstatus.apirpgstatus.model.Items.Relacoes.Atributos_Usuarios;
import br.com.apirpgstatus.apirpgstatus.model.Keys.AtributosUsuariosKey;

@Repository
@Table(name="Atributos_Usuarios")
public interface IAtributos_UsuariosRepository extends JpaRepository<Atributos_Usuarios, AtributosUsuariosKey>{

}
