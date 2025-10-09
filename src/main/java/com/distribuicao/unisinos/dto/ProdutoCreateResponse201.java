package com.distribuicao.unisinos.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoCreateResponse201 {
	
	
    private String nome;
    private String sku;
    private Integer fornecedorId;
    private Integer quantidadeTotal;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    
    ProdutoCreateResponse201() {
    	
    }
    
    ProdutoCreateResponse201(String nome, String sku, Integer fornecedorId, int quantidadeTotal, BigDecimal precoCusto, BigDecimal precoVenda) {
    	this.nome = nome;
    	this.sku = sku;
    	this.fornecedorId = fornecedorId;
    	this.quantidadeTotal = quantidadeTotal;
    	this.precoCusto = precoCusto;
    	this.precoVenda = precoVenda;
    }
}
