package com.distribuicao.unisinos.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoCreateDTO {
	private String nome;
    private String sku;
    private Integer fornecedorId;
    private int quantidadeTotal;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
}
