package com.sistemaveterinaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    private String raza;

    private int edad;

    @ManyToOne
    @JoinColumn(name = "dueno_id", nullable = false)
    private Dueno dueno; // Relación con el dueño

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cita> citas; // Relación con citas
}
