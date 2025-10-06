package com.distribuicao.unisinos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distribuicao.unisinos.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    /**
     * Busca um Vendedor pelo seu email.
     *
     * @param e-mail a ser pesquisado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Vendedor> findByEmail(String email);
    
    /**
     * Busca um Vendedor pelo nome.
     *
     * @param nome a ser pesquisado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    List<Vendedor> findByNome(String nome);
}