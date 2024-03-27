package com.masantello.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private String grauInstrucao; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OrderService> list = new ArrayList<>();
	
	public Tecnico() {
		super();
	}
	
	public Tecnico(Integer id, String nome, String cpf, String telefone, String grauInstrucao) {
		super(id, nome, cpf, telefone);
		this.setGrauInstrucao(grauInstrucao);
	}

	public List<OrderService> getList() {
		return list;
	}

	public void setList(List<OrderService> list) {
		this.list = list;
	}

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}
	
}
