package com.distribuicao.unisinos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distribuicao.unisinos.model.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    /**
     * Busca um supervisor pelo seu email.
     *
     * @param e-mail a ser pesquisado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Supervisor> findByEmail(String email);
    
    /**
     * Busca um supervisor pelo nome.
     *
     * @param nome a ser pesquisado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    List<Supervisor> findByNome(String nome);
}
