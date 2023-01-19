package com.masantello.demo.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.masantello.demo.models.OrderService;
import com.masantello.demo.models.enums.Prioridade;
import com.masantello.demo.models.enums.Status;

public class OrderServiceDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	private Integer status;
	@NotEmpty(message = "O campo OBSERVAÇÕES é obrigatório")
	private String observacoes;
	private Integer tecnico;
	private Integer cliente;
	
	public OrderServiceDTO() {
		super();
	}
	
	public OrderServiceDTO(Integer id, LocalDateTime dataAbertura, LocalDateTime dataFechamento,
			Integer prioridade, Integer status, String observacoes, 
			Integer tecnico, Integer cliente) {
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.prioridade = prioridade;
		this.status = status;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
	}
	
	public OrderServiceDTO(OrderService orderService) {
		this.id = orderService.getId();
		this.dataAbertura = orderService.getDataAbertura();
		this.dataFechamento = orderService.getDataFechamento();
		this.prioridade = orderService.getPrioridade().getCodigo();
		this.status = orderService.getStatus().getCodigo();
		this.observacoes = orderService.getObservacoes();
		this.tecnico = orderService.getTecnico().getId();
		this.cliente = orderService.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
}
