package com.tfalcon.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfalcon.sacola.enums.FormaPagamento;

import javax.persistence.*;
import java.util.List;

public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;

    @Enumerated
    private FormaPagamento formaPagamento;

    private boolean fechada;
}
