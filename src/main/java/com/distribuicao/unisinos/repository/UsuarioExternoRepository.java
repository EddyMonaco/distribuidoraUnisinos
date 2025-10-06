package com.distribuicao.unisinos.repository;

import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.model.UsuarioExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioExternoRepository extends JpaRepository<UsuarioExterno, Integer> {

    /**
     * Busca um usuário externo pelo seu CNPJ ou CPF.
     *
     * @param cnpjCpf O CNPJ/CPF a ser pesquisado.
     * @return Um Optional contendo o usuário externo, se encontrado.
     */
    Optional<UsuarioExterno> findUsuarioExternoByCnpjCpf(String cnpjCpf);

    /**
     * Lista todos os usuários externos de um determinado tipo (CLIENTE ou FORNECEDOR).
     *
     * @param tipo O tipo de usuário externo.
     * @return Uma lista de usuários externos.
     */
    List<UsuarioExterno> findUsuarioExternoByTipoExterno(UsuarioExterno.TipoExterno tipo);
    
    List<UsuarioExterno> findByNomeContainingIgnoreCase(String nome);
    
    Optional<UsuarioExterno> findUsuarioExternoByEmail(String email);
}
