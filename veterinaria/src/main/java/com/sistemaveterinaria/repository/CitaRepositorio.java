package com.sistemaveterinaria.repository;

import com.sistemaveterinaria.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
