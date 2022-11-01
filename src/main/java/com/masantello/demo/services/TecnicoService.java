package com.masantello.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.controllers.exceptions.DataIntegrityViolationsException;
import com.masantello.demo.dtos.TecnicoDTO;
import com.masantello.demo.models.Tecnico;
import com.masantello.demo.repositories.TecnicoRepository;
import com.masantello.demo.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	//CREATE
	public Tecnico create(TecnicoDTO tecnico) {
		Tecnico obj = null;
		if (findByCPF(tecnico) != null) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		if (tecnico != null) {			
			obj = this.repository.save(new Tecnico(null, tecnico.getNome(), tecnico.getCpf(), tecnico.getTelefone()));
		}
		return obj;
	}
	
	public Tecnico findByCPF(TecnicoDTO objDTO) {
		Tecnico ret = repository.findByCPF(objDTO.getCpf());
		if (ret != null) {
			return ret;
		}
		return null;
	}
	
	//LIST ALL
	public List<Tecnico> findAll(){
		return this.repository.findAll();
	}
	
	//LIST BY ID
	public Tecnico findById(Integer id) {
		Optional<Tecnico> object = this.repository.findById(id);
		
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id
				+ " Tipo de objeto: " + Tecnico.class.getName()));
	}
	
	//UPDATE
	public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {
		Tecnico oldTecnico = findById(id);
		if (findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		
		oldTecnico.setNome(objDto.getNome());
		oldTecnico.setCpf(objDto.getCpf());
		oldTecnico.setTelefone(objDto.getTelefone());
		
		return repository.save(oldTecnico);
	}
	
	
	//DELETE ALL
	public void deleteAll() {
		repository.deleteAll();
	}
	
	//DELETE BY ID
	public void deleteById(Integer id) {
		Tecnico tecnico = findById(id);
		
		if (tecnico.getList().size() > 0) {
			throw new DataIntegrityViolationsException("Técnico possui Ordens de Serviços cadastradas, não pode ser excluído");
		}
		
		repository.delete(tecnico);
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
