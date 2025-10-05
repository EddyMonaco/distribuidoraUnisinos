package com.distribuicao.unisinos.repository;

import com.distribuicao.unisinos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    /**
     * Busca um produto pelo seu código SKU (Stock Keeping Unit).
     *
     * @param sku O SKU do produto.
     * @return Um Optional contendo o produto, se encontrado.
     */
    Optional<Produto> findBySku(String sku);

    /**
     * Busca produtos cujo nome contém o texto fornecido (case-insensitive).
     * Útil para funcionalidades de busca.
     *
     * @param nome O texto a ser buscado no nome dos produtos.
     * @return Uma lista de produtos que correspondem ao critério.
     */
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
}
