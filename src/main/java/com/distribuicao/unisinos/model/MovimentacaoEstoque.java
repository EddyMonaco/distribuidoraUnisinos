package com.distribuicao.unisinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "movimentacoes_estoque")
@Getter
@Setter
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_estoque_id", nullable = false)
    private AreaEstoque areaEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(nullable = false)
    private int quantidade;

    @Column(name = "data_movimentacao", updatable = false)
    private LocalDateTime dataMovimentacao = LocalDateTime.of(LocalDate.now(), LocalTime.now());

    private String observacao;

    public enum TipoMovimentacao {
        ENTRADA,
        SAIDA,
        TRANSFERENCIA
    }
}
