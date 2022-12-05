package com.masantello.demo.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.masantello.demo.models.Tecnico;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo NOME é obrigatório")
	private String nome;
	
	@NotEmpty(message = "O campo CPF é obrigatório")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é obrigatório")
	private String telefone;

	@NotEmpty(message = "O campo GRAU DE INSTRUÇÃO é obrigatório")
	private String grauInstrucao;
	
	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.telefone = tecnico.getTelefone();
		this.grauInstrucao = tecnico.getGrauInstrucao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}
	
}
