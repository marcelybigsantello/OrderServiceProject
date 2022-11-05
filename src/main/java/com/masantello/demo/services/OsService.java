package com.masantello.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.masantello.demo.dtos.OrderServiceDTO;
import com.masantello.demo.models.Cliente;
import com.masantello.demo.models.OrderService;
import com.masantello.demo.models.Tecnico;
import com.masantello.demo.models.enums.Prioridade;
import com.masantello.demo.models.enums.Status;
import com.masantello.demo.repositories.OsRepository;
import com.masantello.demo.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	public OrderService create(@Valid OrderServiceDTO obj) {
		OrderService orderService = new OrderService();
		orderService.setId(obj.getId());
		orderService.setDataAbertura(obj.getDataAbertura());
		orderService.setDataFechamento(obj.getDataFechamento());
		orderService.setObservacoes(obj.getObservacoes());
		orderService.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		orderService.setStatus(Status.toEnum(obj.getStatus()));
		
		Cliente cliente = clienteService.listById(obj.getCliente());
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		
		orderService.setCliente(cliente);
		orderService.setTecnico(tecnico);
		
		return repository.save(orderService);
	}

	public OrderService listById(Integer id) {
		Optional<OrderService> orderService = repository.findById(id);
		if (!orderService.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + OrderService.class.getSimpleName());
		}

		return orderService.get();
	}

	public List<OrderService> listAll() {
		return this.repository.findAll();
	}

	public OrderService update(Integer id, @Valid OrderServiceDTO objDto) {
		OrderService os = listById(id);
		
		if (os != null) {
			os.setDataAbertura(objDto.getDataAbertura());
			os.setDataFechamento(objDto.getDataFechamento());
			os.setObservacoes(objDto.getObservacoes());
//			os.setPrioridade(objDto.getPrioridade().);
//			os.setStatus(objDto.getStatus().getCodigo());
//			os.setTecnico(objDto.getTecnico().getClass());
//			os.setCliente(objDto.getCliente().getId());
		}
		
		return os;
	}

	public void delete(Integer id) {
		OrderService orderService = listById(id);
		
		if (!orderService.getStatus().getDescricao().equalsIgnoreCase("Encerrado")) {
			throw new DataIntegrityViolationException("Ordem de serviço não concluída, não pode ser excluída!");
		}
		
		this.repository.deleteById(id);
	}

	

}
