package com.masantello.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.masantello.demo.dtos.CostumerDTO;
import com.masantello.demo.models.Customer;
import com.masantello.demo.services.CustomerService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class CostumerController {
	
	@Autowired
	private CustomerService service;

	/*
	 * Método CREATE
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CostumerDTO> create(@Valid @RequestBody CostumerDTO novo){
		Customer novoCliente = service.create(novo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoCliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/*
	 * Método LIST ALL
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CostumerDTO>> listAll(){
		List<Customer> lista = service.findAll();
		List<CostumerDTO> ret = lista.stream().map(obj -> new CostumerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ret);
	}
	
	/*
	 * Método LIST BY ID
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<CostumerDTO> listById(@PathVariable Integer id){
		Customer cliente = service.listById(id);
		CostumerDTO clienteDTO = new CostumerDTO(cliente);
		return ResponseEntity.ok().body(clienteDTO);
	}
	
	/*
	 * Método UPDATE
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<CostumerDTO> update(@PathVariable Integer id, @Valid @RequestBody CostumerDTO objDto){
		CostumerDTO novoCliente = new CostumerDTO(service.update(id, objDto));
		return ResponseEntity.ok().body(novoCliente);
	}
	
	
	/*
	 * Método DELETE
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
