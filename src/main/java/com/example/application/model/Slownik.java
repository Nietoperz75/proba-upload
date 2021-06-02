package com.example.application.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "slownik")
public class Slownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slowId")
    private Long slowId;

    @Column(name = "RecNo")
    private Integer recNo;

    @Column(name = "LongGate")
    private String longGate;

    @Column(name = "description")
    private String description;

    @Column(name = "idStacji")
    private Integer idStacji;

    @Column(name = "rodzajBramki")
    private Character rodzajBramki;

    @Column(name = "rodzajPomiaru")
    private Character rodzajPomiaru;

    @Column(name = "gateId")
    private Integer gateId;

    @Column(name = "minimum", nullable = true)
    private Integer minimum;

    @Column(name = "maksimum", nullable = true)
    private Integer maksimum;

    

}
