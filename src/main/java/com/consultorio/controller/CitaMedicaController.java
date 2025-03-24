package com.consultorio.controller;

import com.consultorio.database.DatabaseConnection;
import com.consultorio.model.CitaMedica;
import com.consultorio.model.CitaMedicaDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CitaMedicaController {

    // Instancia del DAO para obtener datos
    private final CitaMedicaDAO citaMedicaDAO;

    // Constructor donde se inicializa el DAO
    public CitaMedicaController() {
        this.citaMedicaDAO = new CitaMedicaDAO();
    }

    @GetMapping("/citas")
    public List<CitaMedica> obtenerCitas() {
        // Llamamos al DAO para obtener todas las citas
        return citaMedicaDAO.obtenerTodasLasCitas();
    }
    public void mostrarCitas() {
        // Llamamos al DAO para obtener todas las citas
        citaMedicaDAO.mostrarCitas();
    }

}
