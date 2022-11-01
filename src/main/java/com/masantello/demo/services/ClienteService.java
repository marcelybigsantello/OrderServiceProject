package com.masantello.demo.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.controllers.exceptions.DataIntegrityViolationsException;
import com.masantello.demo.dtos.ClienteDTO;
import com.masantello.demo.models.Cliente;
import com.masantello.demo.models.Pessoa;
import com.masantello.demo.repositories.ClienteRepository;
import com.masantello.demo.repositories.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente create(@Valid ClienteDTO novo) {
		Cliente obj = null;
		if (findByCPF(novo) != null) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		
		if (novo != null) {
			obj = this.repository.save(new Cliente(null, novo.getNome(), novo.getCpf(), novo.getTelefone()));
		}
		
		return obj;
	}

	private Pessoa findByCPF(ClienteDTO novo) {
		Pessoa pessoa = pessoaRepository.findByCPF(novo.getCpf());
		if (pessoa != null) {
			return pessoa;
		}
		return null;
	}

	public List<Cliente> findAll() {
		return this.repository.findAll();
	}
	
	
}
