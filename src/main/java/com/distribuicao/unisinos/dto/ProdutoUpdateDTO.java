package com.distribuicao.unisinos.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoUpdateDTO {
	@Schema(description = "Nome do produto", example = "Camiseta Polo")
    private String nome;

    @Schema(description = "SKU do produto", example = "SKU12345")
    private String sku;

    @Schema(description = "ID do fornecedor", example = "1")
    private Integer fornecedorId;

    @Schema(description = "Quantidade total em estoque", example = "100")
    private Integer quantidadeTotal;

    @Schema(description = "Preço de custo", example = "20.50")
    private BigDecimal precoCusto;

    @Schema(description = "Preço de venda", example = "35.99")
    private BigDecimal precoVenda;
}
