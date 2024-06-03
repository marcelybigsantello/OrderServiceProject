package com.masantello.demo.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.masantello.demo.models.enums.Priority;
import com.masantello.demo.models.enums.Status;

@Entity
public class OrderService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	private Integer status;
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public OrderService() {
		super();
		this.setDataAbertura(LocalDateTime.now());
		this.setPrioridade(Priority.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public OrderService(Integer id, LocalDateTime dataAbertura, Priority prioridade,
			Status status, String observacoes, Technician technician, Customer customer) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());;
		this.prioridade = (prioridade == null) ? 0 : prioridade.getCodigo() ;
		this.status = (status == null) ? 0 : status.getCodigo();
		this.observacoes = observacoes;
		this.technician = technician;
		this.customer = customer;
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

	public Priority getPrioridade() {
		return Priority.toEnum(this.prioridade);
	}

	public void setPrioridade(Priority prioridade) {
		this.prioridade = prioridade.getCodigo();
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCodigo();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderService other = (OrderService) obj;
		return Objects.equals(id, other.id);
	}

}
