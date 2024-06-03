package com.masantello.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.controllers.exceptions.DataIntegrityViolationsException;
import com.masantello.demo.dtos.TechnicianDTO;
import com.masantello.demo.models.Person;
import com.masantello.demo.models.Technician;
import com.masantello.demo.repositories.PersonRepository;
import com.masantello.demo.repositories.TechnicianRepository;
import com.masantello.demo.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository repository;
	
	@Autowired
	private PersonRepository pessoaRepository;
	
	//CREATE
	public Technician create(TechnicianDTO tecnico) {
		Technician obj = null;
		if (findByCPF(tecnico) != null) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		if (tecnico != null) {			
			obj = this.repository.save(new Technician(null, tecnico.getNome(), tecnico.getCpf(), 
					tecnico.getTelefone(), tecnico.getGrauInstrucao()));
		}
		return obj;
	}
	
	public Person findByCPF(TechnicianDTO objDTO) {
		Person ret = pessoaRepository.findByCPF(objDTO.getCpf());
		if (ret != null) {
			return ret;
		}
		return null;
	}
	
	//LIST ALL
	public List<Technician> findAll(){
		return this.repository.findAll();
	}
	
	//LIST BY ID
	public Technician findById(Integer id) {
		Optional<Technician> object = this.repository.findById(id);
		
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id
				+ " Tipo de objeto: " + Technician.class.getSimpleName()));
	}
	
	//UPDATE
	public Technician update(Integer id, @Valid TechnicianDTO objDto) {
		Technician oldTecnico = findById(id);
		if (findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		
		oldTecnico.setNome(objDto.getNome());
		oldTecnico.setCpf(objDto.getCpf());
		oldTecnico.setTelefone(objDto.getTelefone());
		oldTecnico.setGrauInstrucao(objDto.getGrauInstrucao());
		
		return this.repository.save(oldTecnico);
	}
	
	
	//DELETE ALL
	public void deleteAll() {
		this.repository.deleteAll();
	}
	
	//DELETE BY ID
	public void deleteById(Integer id) {
		Technician tecnico = findById(id);
		
		if (tecnico.getList().size() > 0) {
			throw new DataIntegrityViolationsException("Técnico possui Ordens de Serviços cadastradas, não pode ser excluído");
		}
		
		this.repository.delete(tecnico);
	}

	
	//DELETE BY ENTITY
	public void deleteByEntity(Integer id) {
		if (id != null) {
			Optional<Technician> ret = this.repository.findById(id);
			if (ret != null) {
				this.repository.delete(ret.get());
			}
		}
	}

}
