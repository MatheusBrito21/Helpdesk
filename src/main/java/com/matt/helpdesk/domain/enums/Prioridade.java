package com.matt.helpdesk.domain.enums;

public enum Prioridade {
	//perfis padrao
	ALTA(0,"ALTA"),MEDIA(1,"MEDIA"),BAIXA(2,"BAIXA");
	
	private Integer cod;
	private String descricao;
	
	private Prioridade(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Prioridade valor : Prioridade.values()) {
			if(cod.equals(valor.getCod())) {
				return valor;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}

	
}
