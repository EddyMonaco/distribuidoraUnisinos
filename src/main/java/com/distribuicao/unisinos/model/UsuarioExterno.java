package com.distribuicao.unisinos.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios_externos")
@Getter @Setter
public class UsuarioExterno extends Usuario {

    @Column(name = "cnpj_cpf", nullable = false, unique = true)
    private String cnpjCpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_externo", nullable = false)
    private TipoExterno tipoExterno;

    @OneToMany(mappedBy = "usuarioExterno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

    public enum TipoExterno {
        CLIENTE,
        FORNECEDOR
    }
}