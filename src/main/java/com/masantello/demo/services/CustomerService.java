package com.masantello.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.controllers.exceptions.DataIntegrityViolationsException;
import com.masantello.demo.dtos.CostumerDTO;
import com.masantello.demo.models.Customer;
import com.masantello.demo.models.Person;
import com.masantello.demo.repositories.CustomerRepository;
import com.masantello.demo.repositories.PersonRepository;
import com.masantello.demo.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private PersonRepository pessoaRepository;

	public Customer create(@Valid CostumerDTO novo) {
		Customer obj = null;
		if (findByCPF(novo) != null) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		
		if (novo != null) {
			obj = this.repository.save(new Customer(null, novo.getNome(), novo.getCpf(), novo.getTelefone(), 
					 novo.getDataNascimento(), novo.getProfissao()));
		}
		
		return obj;
	}

	private Person findByCPF(CostumerDTO novo) {
		Person pessoa = pessoaRepository.findByCPF(novo.getCpf());
		if (pessoa != null) {
			return pessoa;
		}
		return null;
	}

	public List<Customer> findAll() {
		return this.repository.findAll();
	}

	public Customer listById(Integer id) {
		Optional<Customer> cliente = repository.findById(id);
		
		if (!cliente.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id 
					+ " Tipo de objeto: " + Customer.class.getSimpleName());
		}
		
		return cliente.get();
	}

	public Customer update(Integer id, @Valid CostumerDTO objDto) {
		Customer cliente = listById(id);
		if (findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
			throw new DataIntegrityViolationsException("CPF já cadastrado na base de dados!");
		}
		
		cliente.setNome(objDto.getNome());
		cliente.setCpf(objDto.getCpf());
		cliente.setTelefone(objDto.getTelefone());
		cliente.setDataNascimento(objDto.getDataNascimento());
		cliente.setProfissao(objDto.getProfissao());
		
		return repository.save(cliente);
	}

	public void delete(Integer id) {
		Customer cliente = listById(id);
		
		if (cliente.getList().size() > 0) {
			throw new DataIntegrityViolationsException("O cliente possui Ordens de Serviços cadastradas! Não pode remover esse cliente");
		}
		
		repository.delete(cliente);
	}	
}
