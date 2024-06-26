package com.masantello.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masantello.demo.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

	@Query("SELECT obj FROM Person obj WHERE obj.cpf =:cpf")
	public Person findByCPF(@Param("cpf") String cpf);
}	
