package com.sistemaveterinaria.controller;

import com.sistemaveterinaria.model.Cita;
import com.sistemaveterinaria.service.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CitaControlador {

    @Autowired
    private CitaServicio citaServicio;

    // Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodasLasCitas() {
        try {
            List<Cita> citas = citaServicio.obtenerTodasLasCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        try {
            if (cita == null) {
                return ResponseEntity.badRequest().build();
            }
            Cita citaCreada = citaServicio.crearCita(cita);
            return ResponseEntity.ok(citaCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Actualizar una cita
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            Cita citaActualizada = citaServicio.actualizarCita(id, cita);
            return citaActualizada != null ?
                    ResponseEntity.ok(citaActualizada) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Eliminar una cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        try {
            citaServicio.eliminarCita(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Listar citas en un rango de fechas
    @GetMapping("/rango")
    public ResponseEntity<List<Cita>> obtenerCitasEnRango(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin) {
        try {
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
            LocalDateTime fin = LocalDateTime.parse(fechaFin);
            List<Cita> citas = citaServicio.obtenerCitasEnRango(inicio, fin);
            return ResponseEntity.ok(citas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}