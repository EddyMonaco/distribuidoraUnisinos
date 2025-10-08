package com.distribuicao.unisinos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuicao.unisinos.dto.ProdutoCreateDTO;
import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.service.ProdutosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento dos produtos")
public class ProdutoController {
	@Autowired
    private ProdutosService produtosService;
	
	@Operation(summary = "Listar todos os usuários externos", description = "Retorna uma lista completa de todos os usuários externos cadastrados.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Lista de produtos encontrada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao tentar buscar produtos.")
    })
	@GetMapping
	public List<Produto> findAll(){ 
		return produtosService.findAll();
	}
	
	@Operation(summary = "Listar todos os usuários externos", description = "Retorna uma lista completa de todos os usuários externos cadastrados.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao tentar buscar produto.")
    })
	@GetMapping("/bySku/{sku}")
	public Optional<Produto> findBySku(@PathVariable String sku) {
		return produtosService.findBySku(sku);
	}
	
	@Operation(summary = "Listar todos os usuários externos", description = "Retorna uma lista completa de todos os usuários externos cadastrados.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao tentar buscar produtos.")
    })
	@GetMapping("/byNome/{nome}")
	public List<Produto> findByNomeContainingIgnoreCase(@PathVariable String nome) {
		return produtosService.findByNomeContainingIgnoreCase(nome);
	}
	
	@Operation(summary = "Listar todos os usuários externos", description = "Retorna uma lista completa de todos os usuários externos cadastrados.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do produto."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao tentar criar produto.")
    })
	@PostMapping
	public void createProduto(@RequestBody ProdutoCreateDTO produtoCreateDTO) {
		produtosService.createProduto(produtoCreateDTO);
	}
}