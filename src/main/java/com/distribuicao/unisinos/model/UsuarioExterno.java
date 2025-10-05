package com.distribuicao.unisinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios_externos")
@Getter
@Setter
public class UsuarioExterno extends Usuario {

    @Column(name = "cnpj_cpf", nullable = false, unique = true)
    private String cnpjCpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_externo", nullable = false)
    private TipoExterno tipoExterno;

    @OneToMany(mappedBy = "usuarioExterno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<>();

    public enum TipoExterno {
        CLIENTE,
        FORNECEDOR
    }
}

