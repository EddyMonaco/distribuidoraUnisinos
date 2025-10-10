package com.distribuicao.unisinos.repository;

import com.distribuicao.unisinos.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, Integer> {

    /**
     * Lista todas as movimentações de um produto específico.
     *
     * @param produtoId O ID do produto.
     * @return Uma lista de movimentações.
     */
    List<MovimentacaoEstoque> findByProdutoId(Integer produtoId);

    /**
     * Lista todas as movimentações ocorridas em um determinado período.
     *
     * @param inicio A data/hora de início do período.
     * @param fim A data/hora de fim do período.
     * @return Uma lista de movimentações.
     */
    List<MovimentacaoEstoque> findByDataMovimentacaoBetween(Instant inicio, Instant fim);

}
