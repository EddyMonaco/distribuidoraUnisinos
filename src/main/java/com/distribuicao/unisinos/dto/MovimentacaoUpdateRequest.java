package com.distribuicao.unisinos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoUpdateRequest {
	
    private Integer usuarioId;
    
    private Integer quantidade;

    private String observacao;

    private String codigoArea;
}
