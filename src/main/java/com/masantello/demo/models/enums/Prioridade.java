package com.masantello.demo.models.enums;

public enum Prioridade {

	BAIXA(0, "Baixa"), MEDIA(1, "Media"), ALTA(2, "Alta");

	private Integer codigo;
	private String descricao;

	private Prioridade() {

	}

	private Prioridade(int codigo, String desc) {
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

	public static Prioridade findByCode(int codigo) {
		for (Prioridade prioridade : Prioridade.values()) {
			if (prioridade.getCodigo() == codigo) {
				return prioridade;
			}
		}
		return null;
	}

	public static Prioridade findByDescription(String descricao) {
		for (Prioridade prioridade : Prioridade.values()) {
			if (prioridade.getDescricao().equalsIgnoreCase(descricao)) {
				return prioridade;
			}
		}
		return null;
	}
	
	public static Prioridade toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (Prioridade p: Prioridade.values()) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida! " + codigo);
	}

}
