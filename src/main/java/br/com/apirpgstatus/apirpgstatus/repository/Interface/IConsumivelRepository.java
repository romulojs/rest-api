package br.com.apirpgstatus.apirpgstatus.repository.Interface;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apirpgstatus.apirpgstatus.model.Items.Consumivel;

@Repository
@Table(name="Consumiveis")
public interface IConsumivelRepository extends JpaRepository<Consumivel, Long>{

}
