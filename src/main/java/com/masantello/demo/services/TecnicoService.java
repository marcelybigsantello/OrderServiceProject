package com.masantello.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.models.Tecnico;
import com.masantello.demo.repositories.TecnicoRepository;
import com.masantello.demo.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	//CREATE
	public Tecnico create(Tecnico tecnico) {
		Tecnico obj = null;
		if (tecnico != null) {			
			obj = repository.save(tecnico);
		}
		return obj;
	}
	
	//LIST ALL
	public List<Tecnico> findAll(){
		List<Tecnico> list = repository.findAll();
		return list;
	}
	
	//LIST BY ID
	public Tecnico findById(Integer id) {
		Optional<Tecnico> object = repository.findById(id);
		
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id
				+ "Tipo de objeto: " + Tecnico.class.getName()));
	}
	
	//UPDATE
	public void update(Tecnico tecnico) {
		if (tecnico != null) {			
			repository.saveAndFlush(tecnico);
		}
	}
	
	//DELETE ALL
	public void deleteAll() {
		repository.deleteAll();
	}
	
	//DELETE BY ID
	public void deleteById(Integer id) {
		if (id != null) {
			Optional<Tecnico> ret = repository.findById(id);
			if (ret != null) {
				repository.deleteById(id);
			}
		}
	}

	
	//DELETE BY ENTITY
	public void deleteByEntity(Integer id) {
		if (id != null) {
			Optional<Tecnico> ret = repository.findById(id);
			if (ret != null) {
				repository.delete(ret.get());
			}
		}
	}
	
}
