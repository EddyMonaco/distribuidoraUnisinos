package com.distribuicao.unisinos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioCreateDTO {

	@Schema(description = "Nome do usuário", example = "João Carlos")
	private String nome;
	
	@Schema(description = "Email do usuário", example = "usuario.teste@teste.com")
	private String email;
}
