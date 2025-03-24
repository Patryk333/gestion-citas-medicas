package com.consultorio.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Scanner;
import com.consultorio.model.Medico;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @PostMapping
    public Medico registrarMedico(@RequestBody Medico medico) {
        // Lógica para registrar médico
        System.out.println("Ingrese el nombre del médico:");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la especialidad del médico:");
        String especialidad = scanner.nextLine();

        // Simulando el registro del médico
        medico = new Medico(1, nombre, especialidad);
        System.out.println("Médico registrado: " + medico.getNombre());

        return medico;
    }
}
