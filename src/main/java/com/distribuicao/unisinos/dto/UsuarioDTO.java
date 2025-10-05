package com.distribuicao.unisinos.dto;

import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.model.UsuarioExterno;

import lombok.Getter;
import lombok.Setter;

public record UsuarioDTO(
    Integer id,
    String nome,
    String email,
    String tipo,
    String cnpjCpf 
) {
    
    public UsuarioDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario instanceof UsuarioExterno ? ((UsuarioExterno) usuario).getTipoExterno().name() : "INTERNO",
            usuario instanceof UsuarioExterno ? ((UsuarioExterno) usuario).getCnpjCpf() : null
        );
    }
}
