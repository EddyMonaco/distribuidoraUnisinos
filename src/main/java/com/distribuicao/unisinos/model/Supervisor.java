package com.distribuicao.unisinos.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
@DiscriminatorValue("SUPERVISOR")
public class Supervisor extends Usuario {

}
