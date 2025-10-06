package com.distribuicao.unisinos.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SUPERVISOR")
public class Supervisor extends Usuario {

}
