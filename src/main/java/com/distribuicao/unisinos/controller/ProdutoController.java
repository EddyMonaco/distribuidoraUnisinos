package com.distribuicao.unisinos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuicao.unisinos.dto.ProdutoCreateDTO;
import com.distribuicao.unisinos.dto.ProdutoCreateResponse201;
import com.distribuicao.unisinos.dto.ProdutoUpdateDTO;
import com.distribuicao.unisinos.dto.ResponseError;
import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.service.ProdutosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento dos produtos")
public class ProdutoController {
	@Autowired
    private ProdutosService produtosService;
	
	@Operation(summary = "Listar todos os produtos", description = "Retorna uma lista completa de todos os produtos cadastrados.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Lista de produtos encontrada com sucesso."),
            @ApiResponse(
            		responseCode = "404", 
            		description = "Produto não encontrado.", 
            		content = @Content(
            				mediaType = "application/json",
            				schema = @Schema(implementation = ResponseError.class),
            				examples = @ExampleObject(value = "{ \"mensagem\": \"Produto não encontrado\", \"codigo\": 404 }"
            				)
            		)
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar buscar produto.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao tentar buscar produto\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@GetMapping
	public List<Produto> findAll(){ 
		return produtosService.findAll();
	}
	
	@Operation(summary = "Listar produto pelo código SKU", description = "Retorna o produto equivalente ao código SKU pesquisado.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso."),
            @ApiResponse(
            		responseCode = "404", 
            		description = "Produto não encontrado.", 
            		content = @Content(
            				mediaType = "application/json",
            				schema = @Schema(implementation = ResponseError.class),
            				examples = @ExampleObject(value = "{ \"mensagem\": \"Produto não encontrado\", \"codigo\": 404 }"
            				)
            		)
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar buscar produto.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao tentar buscar produto\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@GetMapping("/bySku/{sku}")
	public Optional<Produto> findBySku(@PathVariable String sku) {
		return produtosService.findBySku(sku);
	}
	
	@Operation(summary = "Listar produtos pelo nome", description = "Retorna uma lista dos produtos equivalentes ao nome pesquisado.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso."),
            @ApiResponse(
            		responseCode = "404", 
            		description = "Produto não encontrado.", 
            		content = @Content(
            				mediaType = "application/json",
            				schema = @Schema(implementation = ResponseError.class),
            				examples = @ExampleObject(value = "{ \"mensagem\": \"Produto não encontrado\", \"codigo\": 404 }"
            				)
            		)
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar buscar produto.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao tentar buscar produto\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@GetMapping("/byNome/{nome}")
	public List<Produto> findByNomeContainingIgnoreCase(@PathVariable String nome) {
		return produtosService.findByNomeContainingIgnoreCase(nome);
	}
	
	@Operation(summary = "Criar um novo produto", description = "Cria um produto com os dados informados no DTO.")
	@ApiResponses(value = { 
            @ApiResponse(
            		responseCode = "201", 
            		description = "Produto criado com sucesso.",
            		content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoCreateResponse201.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"nome\": \"Produto Exemplo\",\n" +
                                    "  \"sku\": \"SKU12345\",\n" +
                                    "  \"fornecedorId\": 10,\n" +
                                    "  \"quantidadeTotal\": 50,\n" +
                                    "  \"precoCusto\": 25.50,\n" +
                                    "  \"precoVenda\": 39.90\n" +
                                    "}"
                            )
                    )
            ),
            @ApiResponse(
            		responseCode = "400", 
            		description = "Dados inválidos para criação do produto.",
            		content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class),
                            examples = @ExampleObject(value = "{ \"mensagem\": \"Dados inválidos\", \"codigo\": 400 }"
                            )
                    )
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar criar produto.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao criar o produto\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@PostMapping
	public ResponseEntity<Void> createProduto(@RequestBody ProdutoCreateDTO produtoCreateDTO) {
		produtosService.createProduto(produtoCreateDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@Operation(summary = "Deletar produto pelo ID", description = "Deleta o produto equivalente ao ID fornecido.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso."),
            @ApiResponse(
            		responseCode = "404", 
            		description = "Produto não encontrado.", 
            		content = @Content(
            				mediaType = "application/json",
            				schema = @Schema(implementation = ResponseError.class),
            				examples = @ExampleObject(value = "{ \"mensagem\": \"Produto não encontrado para deletar\", \"codigo\": 404 }"
            				)
            		)
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar buscar produto.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao tentar deletar produto\", \"codigo\": 500 }"
                    		)
                    )
            )
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProdutoById(@PathVariable Integer id) {
		Optional<Produto> produto = produtosService.findById(id);
		if(produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		produtosService.deleteProdutoById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(
		    summary = "Atualizar produto existente",
		    description = "Atualiza os dados de um produto com base no ID informado."
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "200",
		        description = "Produto atualizado com sucesso",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = ProdutoUpdateDTO.class),
		            examples = @ExampleObject(
		                name = "Exemplo de sucesso",
		                value = """
		                {
		                  "id": 12,
		                  "nome": "Cadeira Gamer RGB",
		                  "sku": "CG-2025-BLK",
		                  "fornecedorId": 3,
		                  "quantidadeTotal": 25,
		                  "precoCusto": 850.00,
		                  "precoVenda": 1299.90
		                }
		                """
		            )
		        )
		    ),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Dados inválidos fornecidos",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = ResponseError.class),
		            examples = @ExampleObject(
		                name = "Erro de validação",
		                value = "{ \"mensagem\": \"Dados inválidos\", \"codigo\": 400 }"
		            )
		        )
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "Produto não encontrado",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = ResponseError.class),
		            examples = @ExampleObject(
		                name = "Produto não encontrado",
		                value = "{ \"mensagem\": \"Produto não encontrado para atualizar\", \"codigo\": 404 }"
		            )
		        )
		    ),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Erro interno no servidor",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(implementation = ResponseError.class),
		            examples = @ExampleObject(
		                name = "Erro interno",
		                value = "{ \"mensagem\": \"Erro interno ao tentar atualizar o produto\", \"codigo\": 500 }"
		            )
		        )
		    )
		})
	@PatchMapping("/atualiza-produto/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody ProdutoUpdateDTO produtoUpdateDTO){
		Optional<Produto> produtoAtualizado = produtosService.updateProdutoById(id, produtoUpdateDTO);
		
		if (produtoAtualizado.isEmpty()) {
	        return ResponseEntity.notFound().build(); // 404
	    }

	    return ResponseEntity.ok(produtoAtualizado.get());
	}
}