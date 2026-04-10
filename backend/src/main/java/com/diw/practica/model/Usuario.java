package com.diw.practica.model; // OBLIGATORIO: Debe coincidir con tu carpeta

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol { ADMIN, ALUMNO, PROFESOR }
}