package com.distribuicao.unisinos.controller;

import com.distribuicao.unisinos.dto.MovimentacaoRequest;
import com.distribuicao.unisinos.dto.MovimentacaoResponse;
import com.distribuicao.unisinos.dto.TransferenciaRequest;
import com.distribuicao.unisinos.model.MovimentacaoEstoque;
import com.distribuicao.unisinos.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/entrada")
    public ResponseEntity<MovimentacaoResponse> entrada(@Valid @RequestBody MovimentacaoRequest req) {
        MovimentacaoEstoque mov = movimentacaoService.registrarEntrada(
                req.getProdutoId(), req.getCodigoArea(), req.getUsuarioId(), req.getQuantidade(), req.getObservacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(mov));
    }

    @PostMapping("/saida")
    public ResponseEntity<MovimentacaoResponse> saida(@Valid @RequestBody MovimentacaoRequest req) {
        MovimentacaoEstoque mov = movimentacaoService.registrarSaida(
                req.getProdutoId(), req.getCodigoArea(), req.getUsuarioId(), req.getQuantidade(), req.getObservacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(mov));
    }

    @PostMapping("/transferencia")
    public ResponseEntity<List<MovimentacaoResponse>> transferencia(@Valid @RequestBody TransferenciaRequest req) {
        List<MovimentacaoEstoque> movs = movimentacaoService.registrarTransferencia(
                req.getProdutoId(), req.getCodigoAreaOrigem(), req.getCodigoAreaDestino(), req.getUsuarioId(), req.getQuantidade(), req.getObservacao());
        List<MovimentacaoResponse> resp = movs.stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<MovimentacaoResponse>> listarPorProduto(@PathVariable Integer produtoId) {
        List<MovimentacaoEstoque> movs = movimentacaoService.listarPorProduto(produtoId);
        List<MovimentacaoResponse> resp = movs.stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/periodo")
    public ResponseEntity<?> listarPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
        try {
            Instant i = Instant.parse(inicio);
            Instant f = Instant.parse(fim);
            List<MovimentacaoEstoque> movs = movimentacaoService.listarPorPeriodo(i, f);
            List<MovimentacaoResponse> resp = movs.stream().map(this::toResponse).collect(Collectors.toList());
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Formato de data inválido. Use ISO-8601, ex: 2023-01-01T00:00:00Z");
        }
    }

    private MovimentacaoResponse toResponse(MovimentacaoEstoque mov) {
        MovimentacaoResponse r = new MovimentacaoResponse();
        r.setId(mov.getId());
        if (mov.getProduto() != null) {
            r.setProdutoId(mov.getProduto().getId());
            r.setProdutoNome(mov.getProduto().getNome());
        }
        if (mov.getAreaEstoque() != null) {
            r.setAreaCodigo(mov.getAreaEstoque().getCodigoArea());
        }
        if (mov.getUsuario() != null) {
            r.setUsuarioId(mov.getUsuario().getId());
        }
        r.setTipo(mov.getTipoMovimentacao() != null ? mov.getTipoMovimentacao().name() : null);
        r.setQuantidade(mov.getQuantidade());
        r.setDataMovimentacao(mov.getDataMovimentacao());
        r.setObservacao(mov.getObservacao());
        return r;
    }

}