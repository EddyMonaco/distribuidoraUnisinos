package com.distribuicao.unisinos.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "areas_estoque")
@Getter
@Setter
public class AreaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_area", nullable = false, unique = true)
    private String codigoArea;

    private String descricao;

}
