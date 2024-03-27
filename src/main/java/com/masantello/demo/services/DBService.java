package com.masantello.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.models.Cliente;
import com.masantello.demo.models.OrderService;
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
		Cliente c1 = new Cliente(null, "Maria Joaquina", "487.880.989-23", "(45) 97894-7894", LocalDate.of(1997, 01, 22), "Analista de Sistemas");
		Cliente c2 = new Cliente(null, "Ana Beatriz Almeida", "993.963.639-36", "(12) 97894-1234", LocalDate.of(2002, 03, 30), "Analista de Qualidade de Software");
		Cliente c3 = new Cliente(null, "Jair Bolsonaro", "209.196.220-11", "(21) 2222-2022", LocalDate.of(1956, 03, 21), "Presidente da República");
		Cliente c4 = new Cliente(null, "Maria Helena", "722.270.669-03", "(21) 2222-2022",  LocalDate.of(1995, 03, 22), "Professora 4ª série");
		Cliente c5 = new Cliente(null, "José Oliveira Costa", "432.204.886-25", "(11) 1234-5677", LocalDate.of(1990, 04, 20), "Analista de Sistemas");
		
		
		//TODO: Criar um enum de Grau de Instrucao
		Tecnico t1 = new Tecnico(null, "Marcos", "467.028.939-06", "(11) 1234-4564", "Graduação");
		Tecnico t2 = new Tecnico(null, "Gabriel Fernandes", "178.361.269-00", "(11) 1234-4444", "Pós graduação");
		Tecnico t3 = new Tecnico(null, "Maria Cristina", "252.297.190-52", "(11) 1234-8888", "Mestrado");
		Tecnico t4 = new Tecnico(null, "Maria Helena Carvalho", "738.258.580-81", "(45) 0777-5050", "Ensino Médio");
	
		OrderService orderService1 = new OrderService(null, LocalDateTime.now(), Prioridade.ALTA, Status.ANDAMENTO, "teste insert", t1, c1);
		OrderService orderService2 = new OrderService(null, LocalDateTime.now(), Prioridade.BAIXA, Status.ABERTO, "Notebook apresenta baixa performance", t2, c3);
		OrderService orderService3 = new OrderService(null, LocalDateTime.now(), Prioridade.MEDIA, Status.ENCERRADO, "Troca de equipamento", t2, c1);
		
		t1.getList().add(orderService1);
		c1.getList().add(orderService1);
		
		t2.getList().add(orderService2);
		c3.getList().add(orderService2);
		
		t2.getList().add(orderService3);
		c1.getList().add(orderService3);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		osRepository.saveAll(Arrays.asList(orderService1, orderService2, orderService3));
	}
}
