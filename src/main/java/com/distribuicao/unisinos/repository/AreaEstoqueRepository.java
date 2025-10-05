package com.distribuicao.unisinos.repository;

import com.distribuicao.unisinos.model.AreaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaEstoqueRepository extends JpaRepository<AreaEstoque, Integer> {

    /**
     * Busca uma área de estoque pelo seu código único.
     *
     * @param codigoArea O código da área.
     * @return Um Optional contendo a área de estoque, se encontrada.
     */
    Optional<AreaEstoque> findByCodigoArea(String codigoArea);
}
