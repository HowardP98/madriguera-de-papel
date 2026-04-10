package com.diw.practica.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anio;
    private String editorial;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado { DISPONIBLE, PRESTADO, RESERVADO }
}