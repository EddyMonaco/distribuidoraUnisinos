package com.distribuicao.unisinos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AreaEstoqueCreateDTO {
	@Schema(description = "Código de área", example = "M01")
    private String codigoArea;
	
	@Schema(description = "Descrição da área", example ="Área de massas")
    private String descricao;
}
