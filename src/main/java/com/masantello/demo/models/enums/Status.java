package com.masantello.demo.models.enums;

public enum Status {

	ABERTO(0, "Aberto"), EM_ANDAMENTO(1, "Em andamento"), ENCERRADO(2, "Encerrado");

	private Integer codigo;
	private String descricao;

	private Status() {

	}

	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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

	public static Status findByCode(Integer code) {
		for (Status status : Status.values()) {
			if (status.getCodigo() == code) {
				return status;
			}
		}

		return null;
	}

	public static Status findByDescription(String descricao) {
		for (Status status : Status.values()) {
			if (status.getDescricao().equalsIgnoreCase(descricao)) {
				return status;
			}
		}

		return null;
	}

	public static Status toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (Status status : Status.values()) {
			if (status.getCodigo() == codigo) {
				return status;
			}
		}

		throw new IllegalArgumentException("Status inválido!" + codigo);
	}
}
