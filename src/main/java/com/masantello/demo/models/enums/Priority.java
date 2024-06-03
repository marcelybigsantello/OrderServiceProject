package com.masantello.demo.models.enums;

public enum Priority {

	BAIXA(0, "Baixa"), MEDIA(1, "Media"), ALTA(2, "Alta");

	private Integer codigo;
	private String descricao;

	private Priority() {

	}

	private Priority(int codigo, String desc) {
		this.codigo = codigo;
		this.descricao = desc;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Priority findByCode(int codigo) {
		for (Priority prioridade : Priority.values()) {
			if (prioridade.getCodigo() == codigo) {
				return prioridade;
			}
		}
		return null;
	}

	public static Priority findByDescription(String descricao) {
		for (Priority prioridade : Priority.values()) {
			if (prioridade.getDescricao().equalsIgnoreCase(descricao)) {
				return prioridade;
			}
		}
		return null;
	}
	
	public static Priority toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (Priority p: Priority.values()) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida! " + codigo);
	}

}
