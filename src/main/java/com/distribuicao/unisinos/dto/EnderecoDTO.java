package com.distribuicao.unisinos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoDTO {

	@Schema(description = "Nome da rua", example = "Avenida dos Municipios")
    private String rua;
    
	@Schema(description = "Número do endereço", example = "0000")
    private Integer numero;
	
	@Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

	@Schema(description = "Nome da cidade", example = "Campo Bom")
    private String cidade;

	@Schema(description = "Estado (UF)", example = "RS")
    private String estado;

	@Schema(description = "CEP do endereço", example = "93544-750")
    private String cep;
}
