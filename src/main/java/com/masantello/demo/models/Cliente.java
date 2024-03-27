package com.masantello.demo.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrderService> list = new ArrayList<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String profissao;
	

	public Cliente() {
		super();
	}

	
	public Cliente(Integer id, String nome, String cpf, String telefone, LocalDate dataNascimento, String profissao) {
		super(id, nome, cpf, telefone);
		this.dataNascimento = dataNascimento;
		this.profissao = profissao;
	}


	public List<OrderService> getList() {
		return list;
	}

	public void setList(List<OrderService> list) {
		this.list = list;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}	
	
}
