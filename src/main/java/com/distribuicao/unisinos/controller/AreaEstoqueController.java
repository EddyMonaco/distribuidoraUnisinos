package com.distribuicao.unisinos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuicao.unisinos.dto.AreaEstoqueCreateDTO;
import com.distribuicao.unisinos.dto.ProdutoCreateResponse201;
import com.distribuicao.unisinos.dto.ResponseError;
import com.distribuicao.unisinos.model.AreaEstoque;
import com.distribuicao.unisinos.service.AreaEstoqueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/area-estoque")
@Tag(name = "Área de estoque", description = "Endpoints para gerenciamento das áreas de estoque")
public class AreaEstoqueController {
	@Autowired
	private AreaEstoqueService areaEstoqueService;
	
	@Operation(summary = "Listar todos as áreas de estoque", description = "Retorna uma lista completa de todas as áreas de estoque cadastradas.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Lista de áreas de estoque encontrada com sucesso."),
            @ApiResponse(
            		responseCode = "404", 
            		description = "Área de estoque não encontrado.", 
            		content = @Content(
            				mediaType = "application/json",
            				schema = @Schema(implementation = ResponseError.class),
            				examples = @ExampleObject(value = "{ \"mensagem\": \"Áreas dos estoques não encontradas\", \"codigo\": 404 }"
            				)
            		)
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar buscar área de estoque.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao tentar buscar área de estoque\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@GetMapping
	public List<AreaEstoque> findAll(){ 
		return areaEstoqueService.findAll();
	}
	
	@Operation(summary = "Listar uma área de estoque", description = "Retorna uma área de estoque conforme o código da área.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Área de estoque encontrada com sucesso."),
            @ApiResponse(
            		responseCode = "404", 
            		description = "Área de estoque não encontrada.", 
            		content = @Content(
            				mediaType = "application/json",
            				schema = @Schema(implementation = ResponseError.class),
            				examples = @ExampleObject(value = "{ \"mensagem\": \"Área de estoque não encontrada\", \"codigo\": 404 }"
            				)
            		)
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar buscar área de estoque.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao tentar buscar área de estoque\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@GetMapping("/byCodigoArea/{codigoArea}")
	public Optional<AreaEstoque> findByCodigoArea(@PathVariable String codigoArea){ 
		return areaEstoqueService.findByCodigoArea(codigoArea);
	}
	
	@Operation(summary = "Criar uma nova área de estoque", description = "Cria uma área de estoque com os dados informados no DTO.")
	@ApiResponses(value = { 
            @ApiResponse(
            		responseCode = "201", 
            		description = "Área de estoque criada com sucesso.",
            		content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoCreateResponse201.class),
                            examples = @ExampleObject(value = "{ \"codigoArea\": \"Area Exemplo\", \"descricao\": \"Area de massas\" }"
                            )
                    )
            ),
            @ApiResponse(
            		responseCode = "400", 
            		description = "Dados inválidos para criação da área de estoque.",
            		content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class),
                            examples = @ExampleObject(value = "{ \"mensagem\": \"Dados inválidos\", \"codigo\": 400 }"
                            )
                    )
            ),
            @ApiResponse(
            		responseCode = "500", 
            		description = "Erro interno ao tentar criar a área de estoque.",
            		content = @Content(
                    		mediaType = "application/json",
                    		schema = @Schema(implementation = ResponseError.class),
                    		examples = @ExampleObject(value = "{ \"mensagem\": \"Erro interno ao criar a área de estoque\", \"codigo\": 500 }"
                    		)
                    )
            )
    })
	@PostMapping
	public ResponseEntity<Void> createProduto(@RequestBody AreaEstoqueCreateDTO areaEstoqueDTO) {
		areaEstoqueService.createAreaEstoque(areaEstoqueDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
