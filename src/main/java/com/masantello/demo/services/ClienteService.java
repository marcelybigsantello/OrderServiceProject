package com.masantello.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.controllers.exceptions.DataIntegrityViolationsException;
import com.masantello.demo.dtos.ClienteDTO;
import com.masantello.demo.models.Cliente;
import com.masantello.demo.models.Pessoa;
import com.masantello.demo.repositories.ClienteRepository;
import com.masantello.demo.repositories.PessoaRepository;
import com.masantello.demo.services.exceptions.ObjectNotFoundException;

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

	public Cliente listById(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (!cliente.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id 
					+ " Tipo de objeto: " + Cliente.class.getSimpleName());
		}
		
		return cliente.get();
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDto) {
		Cliente cliente = listById(id);
		if (findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		
		cliente.setNome(objDto.getNome());
		cliente.setCpf(objDto.getCpf());
		cliente.setTelefone(objDto.getTelefone());
		
		return repository.save(cliente);
	}

	public void delete(Integer id) {
		Cliente cliente = listById(id);
		
		if (cliente.getList().size() > 0) {
			throw new DataIntegrityViolationsException("O cliente possui Ordens de Serviços cadastradas! Não pode remover esse cliente");
		}
		
		repository.delete(cliente);
	}
	
	
}
