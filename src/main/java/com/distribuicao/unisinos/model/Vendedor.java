package com.distribuicao.unisinos.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Usuario {
}
