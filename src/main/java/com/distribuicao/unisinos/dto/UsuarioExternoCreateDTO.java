package com.distribuicao.unisinos.dto;

import java.util.ArrayList;
import java.util.List;

import com.distribuicao.unisinos.model.Endereco;
import com.distribuicao.unisinos.model.UsuarioExterno.TipoExterno;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioExternoCreateDTO {

	@Schema(description = "CNPJ OU CPF do usuário externo", example = "CNPJ:00.000.000/0000-00 CPF:000.000.000-00")
	private String cnpjCpf;
	
	@Schema(description = "Tipo de usuário", example = "FORNECEDOR | CLIENTE")
	private TipoExterno tipoExterno;
	
	@Schema(description = "Endereço do usuário", example = "Rua, Número - Bairro. CEP. Cidade-Estado")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@Schema(description = "Nome do usuário", example = "João Carlos")
	private String nome;
	
	@Schema(description = "Email do usuário", example = "usuario.teste@teste.com")
	private String email;
}
