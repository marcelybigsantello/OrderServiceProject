package com.masantello.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.masantello.demo.dtos.OrderServiceDTO;
import com.masantello.demo.models.OrderService;
import com.masantello.demo.services.OsService;

@RestController
@RequestMapping(value = "/os")
public class OrderServiceController {

	@Autowired
	private OsService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<OrderServiceDTO> findById(@PathVariable Integer id){
		OrderServiceDTO osDto = new OrderServiceDTO(this.service.listById(id));
		return ResponseEntity.ok().body(osDto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OrderServiceDTO>> findAll(){
		List<OrderService> orderServiceList = service.listAll();
		List<OrderServiceDTO> list = orderServiceList.stream().map(obj -> new OrderServiceDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderServiceDTO> create(@Valid @RequestBody OrderServiceDTO obj){
		obj = new OrderServiceDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<OrderServiceDTO> update(@Valid @RequestBody OrderServiceDTO obj){
		obj = new OrderServiceDTO(service.update(obj.getId(), obj));
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
