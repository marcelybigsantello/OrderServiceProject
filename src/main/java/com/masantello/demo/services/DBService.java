package com.masantello.demo.services;

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
		Cliente c1 = new Cliente(null, "Marcely", "487.880.989-23", "(11) 97894-7894");
		Cliente c2 = new Cliente(null, "Daniely", "993.963.639-36", "(11) 97894-1234");
		Cliente c3 = new Cliente(null, "Jair Bolsonaro", "209.196.220-11", "(11) 2222-2022");
		Tecnico t1 = new Tecnico(null, "Marcos", "467.028.939-06", "(11) 1234-4564");
		Tecnico t2 = new Tecnico(null, "Marcos Roberto", "178.361.269-00", "(11) 1234-4444");
		Tecnico t3 = new Tecnico(null, "Maria Cristina", "252.297.190-52", "(11) 1234-8888");
	
		OrderService orderService1 = new OrderService(null, LocalDateTime.now(), Prioridade.ALTA, Status.EM_ANDAMENTO, "teste insert", t1, c1);
		OrderService orderService2 = new OrderService(null, LocalDateTime.now(), Prioridade.BAIXA, Status.ABERTO, "Notebook apresenta baixa performance", t2, c3);
		OrderService orderService3 = new OrderService(null, LocalDateTime.now(), Prioridade.MEDIA, Status.ENCERRADO, "Troca de equipamento", t2, c1);
		
		t1.getList().add(orderService1);
		c1.getList().add(orderService1);
		
		t2.getList().add(orderService2);
		c3.getList().add(orderService2);
		
		t2.getList().add(orderService3);
		c1.getList().add(orderService3);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		osRepository.saveAll(Arrays.asList(orderService1, orderService2, orderService3));
	}
}
