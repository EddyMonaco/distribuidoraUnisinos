package com.distribuicao.unisinos.dto;

import com.distribuicao.unisinos.model.Produto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter	
public class ProdutoDTO {
    private Integer id;
    private String nome;
    private String sku;
    private int quantidadeTotal;
    private BigDecimal precoVenda;
    private String nomeFornecedor;
    private Integer fornecedorId;
    private BigDecimal precoCusto;

    // Map entity to DTO
    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.sku = produto.getSku();
        this.quantidadeTotal = produto.getQuantidadeTotal();
        this.precoVenda = produto.getPrecoVenda();
        this.nomeFornecedor = produto.getFornecedor() != null ? produto.getFornecedor().getNome() : "N/A";
        this.precoCusto = produto.getPrecoCusto();
    }
}