package com.matt.helpdesk.domain.enums;

public enum Status {
	//status padrao
	ABERTO(0,"ABERTO"),ANDAMENTO(1,"ANDAMENTO"),FECHADO(2,"FECHADO");
	
	private Integer cod;
	private String descricao;
	
	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Status valor : Status.values()) {
			if(cod.equals(valor.getCod())) {
				return valor;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}

	
}
