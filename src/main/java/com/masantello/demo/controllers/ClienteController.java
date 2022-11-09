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

import com.masantello.demo.dtos.ClienteDTO;
import com.masantello.demo.models.Cliente;
import com.masantello.demo.services.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	/*
	 * Método CREATE
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO novo){
		Cliente novoCliente = service.create(novo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoCliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/*
	 * Método LIST ALL
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> listAll(){
		List<Cliente> lista = service.findAll();
		List<ClienteDTO> ret = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ret);
	}
	
	/*
	 * Método LIST BY ID
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ClienteDTO> listById(@PathVariable Integer id){
		Cliente cliente = service.listById(id);
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		return ResponseEntity.ok().body(clienteDTO);
	}
	
	/*
	 * Método UPDATE
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto){
		ClienteDTO novoCliente = new ClienteDTO(service.update(id, objDto));
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
