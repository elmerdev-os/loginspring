package com.sistemaveterinaria.service;

import com.sistemaveterinaria.model.Cita;
import com.sistemaveterinaria.repository.CitaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio; // Inyección del repositorio

    // Obtener todas las citas
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepositorio.findAll();
    }

    // Crear una nueva cita
    public Cita crearCita(Cita cita) {
        return citaRepositorio.save(cita);
    }

    // Actualizar una cita
    public Cita actualizarCita(Long id, Cita citaActualizada) {
        citaActualizada.setId(id);
        return citaRepositorio.save(citaActualizada);
    }

    // Eliminar una cita
    public void eliminarCita(Long id) {
        citaRepositorio.deleteById(id);
    }

    // Listar citas en un rango de fechas
    public List<Cita> obtenerCitasEnRango(LocalDateTime inicio, LocalDateTime fin) {
        return citaRepositorio.findByFechaHoraBetween(inicio, fin);
    }
}
