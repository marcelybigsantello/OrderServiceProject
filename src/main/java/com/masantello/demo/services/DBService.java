package com.masantello.demo.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.models.Cliente;
import com.masantello.demo.models.OS;
import com.masantello.demo.models.Tecnico;
import com.masantello.demo.models.enums.Prioridade;
import com.masantello.demo.models.enums.Status;
import com.masantello.demo.repositories.ClienteRepository;
import com.masantello.demo.repositories.OsRepository;
import com.masantello.demo.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private OsRepository osRepository;
 
	public void populaBD() {
		Cliente c1 = new Cliente(null, "Marcely", "487.880.989-23", "(11) 97894-7894");
		Cliente c2 = new Cliente(null, "Daniely", "993.963.639-36", "(11) 97894-1234");
		Tecnico t1 = new Tecnico(null, "Marcos", "467.028.939-06", "(11) 1234-4564");
		Tecnico t2 = new Tecnico(null, "Marcos Roberto", "178.361.269-00", "(11) 1234-4444");
	
		OS os = new OS(null, LocalDateTime.now(), Prioridade.ALTA, Status.ABERTO, "teste insert", t1, c1);
		
		t1.getList().add(os);
		c1.getList().add(os);
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		osRepository.saveAll(Arrays.asList(os));
	}
}
