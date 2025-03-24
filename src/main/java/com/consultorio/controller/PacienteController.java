package com.consultorio.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Scanner;
import com.consultorio.model.Paciente;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        // Lógica para registrar paciente
        System.out.println("Ingrese el nombre del paciente:");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el teléfono del paciente:");
        String telefono = scanner.nextLine();

        // Simulando el registro del paciente
        paciente = new Paciente(1, nombre, telefono);
        System.out.println("Paciente registrado: " + paciente.getNombre());

        return paciente;
    }

    @GetMapping("/historial/{pacienteId}")
    public String consultarHistorialDeCitas(@PathVariable int pacienteId) {
        // Lógica para consultar historial de citas
        System.out.println("Consultando historial de citas para el paciente con ID: " + pacienteId);
        return "Historial de citas consultado para el paciente con ID: " + pacienteId;
    }
}
