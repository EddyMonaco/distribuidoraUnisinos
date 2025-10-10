package com.distribuicao.unisinos.controller;

import com.distribuicao.unisinos.dto.MovimentacaoRequest;
import com.distribuicao.unisinos.dto.MovimentacaoResponse;
import com.distribuicao.unisinos.dto.TransferenciaRequest;
import com.distribuicao.unisinos.dto.MovimentacaoUpdateRequest;
import com.distribuicao.unisinos.model.MovimentacaoEstoque;
import com.distribuicao.unisinos.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimentacao")
@Tag(name = "Movimentação", description = "Endpoints para gerenciamento das movimentações de estoque")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;
    
	@Operation(summary = "Dar entrar em um produto no estoque", description = "Registra uma entrada de produto no estoque, atualizando a quantidade total e criando um registro de movimentação do tipo ENTRADA.")
	@ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Entrada registrada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao tentar registrar a entrada.")
    })
    @PostMapping("/entrada")
    public ResponseEntity<MovimentacaoResponse> entrada(@RequestBody MovimentacaoRequest req) {
        MovimentacaoEstoque mov = movimentacaoService.registrarEntrada(
                req.getProdutoId(), req.getCodigoArea(), req.getUsuarioId(), req.getQuantidade(), req.getObservacao());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(mov));
    }
	
	@Operation(summary = "Dar saída em um produto no estoque", description = "Registra uma saída de produto no estoque, atualizando a quantidade total e criando um registro de movimentação do tipo SAÍDA.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Saída registrada com sucesso."),
			@ApiResponse(responseCode = "500", description = "Erro interno ao tentar registrar a saída.")
	})
    @PostMapping("/saida")
    public ResponseEntity<MovimentacaoResponse> saida(@RequestBody MovimentacaoRequest req) {
        MovimentacaoEstoque mov = movimentacaoService.registrarSaida( req.getProdutoId(), 
        		req.getCodigoArea(), req.getUsuarioId(), req.getQuantidade(), req.getObservacao());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(mov));
    }
	
	@Operation(summary = "Transferir produtos entre áreas de estoque", description = "Registra a transferência de produtos entre duas áreas de estoque, criando dois registros de movimentação: um do tipo SAÍDA na área de origem e outro do tipo ENTRADA na área de destino.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Transferência registrada com sucesso."),
			@ApiResponse(responseCode = "500", description = "Erro interno ao tentar registrar a transferência.")
			})
    @PostMapping("/transferencia")
    public ResponseEntity<List<MovimentacaoResponse>> transferencia(@RequestBody TransferenciaRequest req) {
        List<MovimentacaoEstoque> movs = movimentacaoService.registrarTransferencia(
                req.getProdutoId(), req.getCodigoAreaOrigem(), req.getCodigoAreaDestino(), req.getUsuarioId(), req.getQuantidade(), req.getObservacao());
        List<MovimentacaoResponse> resp = movs.stream()
        								      .map(this::toResponse)
        								      .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
	
	@Operation(summary = "Listar movimentações por produto", description = "Retorna uma lista de todas as movimentações de estoque associadas a um produto específico.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Lista de movimentações encontrada com sucesso."),
			@ApiResponse(responseCode = "500", description = "Erro interno ao tentar buscar movimentações.")
	})
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<MovimentacaoResponse>> listarPorProduto(@PathVariable Integer produtoId) {
        List<MovimentacaoEstoque> movs = movimentacaoService.listarPorProduto(produtoId);
        List<MovimentacaoResponse> resp = movs.stream()
        									  .map(this::toResponse)
        									  .collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

	@Operation(summary = "Listar movimentações por período", description = "Retorna uma lista de todas as movimentações de estoque que ocorreram dentro de um intervalo de datas especificado.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Lista de movimentações encontrada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Formato de data inválido na requisição."), 
			@ApiResponse(responseCode = "500", description = "Erro interno ao tentar buscar movimentações.")
			})
    @GetMapping("/periodo")
    public ResponseEntity<?> listarPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
		Instant exp = Instant.now();
		try {
			Instant i = Instant.parse(inicio);
			Instant f = Instant.parse(fim);
			
            List<MovimentacaoEstoque> movs = movimentacaoService.listarPorPeriodo(i, f);
            List<MovimentacaoResponse> resp = movs.stream()
            									  .map(this::toResponse)
            									  .collect(Collectors.toList());
            return ResponseEntity.ok(resp);
            
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Formato de data inválido. Use ISO-8601 = " + exp.toString());
        }
    }
	
	@Operation(summary = "Deletar uma movimentacao", description = "Deleta uma movimentcao com base no seu ID e se o usuario é supervisor.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Movimentacao deletada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Usuário interno não encontrado para o ID fornecido.")
    })	
    @DeleteMapping("/movimentacao/delete")
    public ResponseEntity<Void> deleteUsuario(@RequestParam  Integer movimentacaoId, @RequestParam Integer usuaorioId) {
        movimentacaoService.deleteMovimentacao(movimentacaoId, usuaorioId);
        return ResponseEntity.noContent().build();
    }

	@Operation(summary = "Atualizar uma movimentação", description = "Atualiza campos de uma movimentação (quantidade, observação, área e usuário). Ajustes na quantidade atualizam o estoque conforme o tipo da movimentação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimentação atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "404", description = "Movimentação ou recurso não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao tentar atualizar a movimentação.")
    })
    @PutMapping("/update")
    public ResponseEntity<MovimentacaoResponse> update(@RequestParam Integer movimentacaoId, @RequestBody MovimentacaoUpdateRequest req) {
        MovimentacaoEstoque mov = movimentacaoService.atualizarMovimentacao(
                movimentacaoId,
                req.getUsuarioId(),
                req.getQuantidade(),
                req.getObservacao(),
                req.getCodigoArea()
        );
        return ResponseEntity.ok(toResponse(mov));
    }

    private MovimentacaoResponse toResponse(MovimentacaoEstoque mov) {
		MovimentacaoResponse responseMovimentacao = new MovimentacaoResponse();
        responseMovimentacao.setId(mov.getId());
        if (mov.getProduto() != null) {
            responseMovimentacao.setProdutoId(mov.getProduto().getId());
            responseMovimentacao.setProdutoNome(mov.getProduto().getNome());
        }
        if (mov.getAreaEstoque() != null) {
            responseMovimentacao.setAreaCodigo(mov.getAreaEstoque().getCodigoArea());
        }
        if (mov.getUsuario() != null) {
            responseMovimentacao.setUsuarioId(mov.getUsuario().getId());
        }
        responseMovimentacao.setTipo(mov.getTipoMovimentacao() != null ? mov.getTipoMovimentacao().name() : null);
        responseMovimentacao.setQuantidade(mov.getQuantidade());
        responseMovimentacao.setDataMovimentacao(mov.getDataMovimentacao());
        responseMovimentacao.setObservacao(mov.getObservacao());
        return responseMovimentacao;
    }

}