package com.masantello.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masantello.demo.models.Customer;
import com.masantello.demo.models.OrderService;
import com.masantello.demo.models.Technician;
import com.masantello.demo.models.enums.Priority;
import com.masantello.demo.models.enums.Status;
import com.masantello.demo.repositories.CustomerRepository;
import com.masantello.demo.repositories.OrderServiceRepository;
import com.masantello.demo.repositories.TechnicianRepository;

@Service
public class DatabaseService {
	
	@Autowired
	private CustomerRepository clienteRepository;
	
	@Autowired
	private TechnicianRepository tecnicoRepository;
	
	@Autowired
	private OrderServiceRepository osRepository;
 
	public void populateDB() {
		Customer c1 = new Customer(null, "Maria Joaquina", "487.880.989-23", "(45) 97894-7894", LocalDate.of(1997, 01, 22), "Analista de Sistemas");
		Customer c2 = new Customer(null, "Ana Beatriz Almeida", "993.963.639-36", "(12) 97894-1234", LocalDate.of(2002, 03, 30), "Analista de Qualidade de Software");
		Customer c3 = new Customer(null, "Jair Bolsonaro", "209.196.220-11", "(21) 2222-2022", LocalDate.of(1956, 03, 21), "Presidente da República");
		Customer c4 = new Customer(null, "Maria Helena", "722.270.669-03", "(21) 2222-2022",  LocalDate.of(1995, 03, 22), "Professora 4ª série");
		Customer c5 = new Customer(null, "José Oliveira Costa", "432.204.886-25", "(11) 1234-5677", LocalDate.of(1990, 04, 20), "Analista de Sistemas");
		
		
		//TODO: Criar um enum de Grau de Instrucao
		Technician t1 = new Technician(null, "Marcos", "467.028.939-06", "(11) 1234-4564", "Graduação");
		Technician t2 = new Technician(null, "Gabriel Fernandes", "178.361.269-00", "(11) 1234-4444", "Pós graduação");
		Technician t3 = new Technician(null, "Maria Cristina", "252.297.190-52", "(11) 1234-8888", "Mestrado");
		Technician t4 = new Technician(null, "Maria Helena Carvalho", "738.258.580-81", "(45) 0777-5050", "Ensino Médio");
	
		OrderService orderService1 = new OrderService(null, LocalDateTime.now(), Priority.ALTA, Status.ANDAMENTO, "teste insert", t1, c1);
		OrderService orderService2 = new OrderService(null, LocalDateTime.now(), Priority.BAIXA, Status.ABERTO, "Notebook apresenta baixa performance", t2, c3);
		OrderService orderService3 = new OrderService(null, LocalDateTime.now(), Priority.MEDIA, Status.ENCERRADO, "Troca de equipamento", t2, c1);
		
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
