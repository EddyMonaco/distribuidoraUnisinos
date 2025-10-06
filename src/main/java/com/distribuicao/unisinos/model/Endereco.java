package com.distribuicao.unisinos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter	
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_externo_id", nullable = false)
    @JsonBackReference
    private UsuarioExterno usuarioExterno;

    @Column(nullable = false)
    private String rua;
    
    @Column(nullable = false)
    private String bairro;
    
    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(name = "cep", nullable = false)
    private String cep;
}
