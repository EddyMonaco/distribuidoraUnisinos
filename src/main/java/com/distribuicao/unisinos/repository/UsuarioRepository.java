package com.distribuicao.unisinos.repository;

import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Busca um usuário pelo seu email.
     *
     * @param e-mail a ser pesquisado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Usuario> findByEmail(String email);
    
    List<Usuario> findByNome(String nome);
}
