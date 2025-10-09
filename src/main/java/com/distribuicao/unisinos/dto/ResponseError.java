package com.distribuicao.unisinos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseError {
	
	private String mensagem;
    private int codigo;

    public ResponseError() {
    }

    public ResponseError(String mensagem, int codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }
}
