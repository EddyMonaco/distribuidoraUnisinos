package com.distribuicao.unisinos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioUpdateDTO {

	@Schema(description = "Nome do usuário", example = "João Carlos")
	private String nome;
	
	@Schema(description = "Email do usuário", example = "usuario.teste@teste.com")
	private String email;
	
	@Schema(description = "Usuário ativado?", example = "ATIVO | INATIVO")
	private Boolean ativo;
}
