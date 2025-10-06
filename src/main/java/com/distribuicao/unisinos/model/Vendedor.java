package com.distribuicao.unisinos.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Usuario {
}
