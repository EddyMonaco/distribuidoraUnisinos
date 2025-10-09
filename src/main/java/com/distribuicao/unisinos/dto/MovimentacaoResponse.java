package com.distribuicao.unisinos.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoResponse {

    private Integer id;
    private Integer produtoId;
    private String produtoNome;
    private String areaCodigo;
    private Integer usuarioId;
    private String tipo;
    private int quantidade;
    private LocalDateTime dataMovimentacao;
    private String observacao;    
     
}
