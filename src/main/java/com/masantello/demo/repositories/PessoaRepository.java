package com.masantello.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masantello.demo.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	public Pessoa findByCPF(@Param("cpf") String cpf);
}	
