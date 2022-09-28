package com.tfalcon.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private double valorUnitario;

    @Builder.Default
    private Boolean disponivel = true;

    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;
}
