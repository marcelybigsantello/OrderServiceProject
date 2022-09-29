package com.masantello.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masantello.demo.dtos.TecnicoDTO;
import com.masantello.demo.models.Tecnico;
import com.masantello.demo.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
	
	TecnicoService service = new TecnicoService();
	
	//CREATE
	public ResponseEntity<TecnicoDTO> create(Tecnico novo) {
		Tecnico tecnico = service.create(novo);
		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnico);
		return ResponseEntity.ok().body(tecnicoDTO);
	}
	
	//LIST ALL
	
	
	//LIST BY ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = service.findById(id);
		TecnicoDTO tecnicoDto = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(tecnicoDto);
	}
	
	//UPDATE
	
	//DELETE ALL
	
	//DELETE BY ID
	
}
