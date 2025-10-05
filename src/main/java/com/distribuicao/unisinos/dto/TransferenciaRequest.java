package com.distribuicao.unisinos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaRequest {

    @NotNull
    private Integer produtoId;

    @NotBlank
    private String codigoAreaOrigem;

    @NotBlank
    private String codigoAreaDestino;

    private Integer usuarioId;

    @Min(1)
    private int quantidade;

    private String observacao;
}
