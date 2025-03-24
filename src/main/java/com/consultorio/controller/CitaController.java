package com.consultorio.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Scanner;
import com.consultorio.model.Cita;
import com.consultorio.model.Paciente;
import com.consultorio.model.Medico;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @PostMapping
    public Cita agendarCita(@RequestBody Cita cita) {
        // Lógica para agendar cita
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha de la cita (dd/MM/yyyy):");
        String fecha = scanner.nextLine();

        // Simulando la creación de una cita
        Paciente paciente = new Paciente(1, "Juan Pérez", "123456789");
        Medico medico = new Medico(1, "Dr. Gómez", "Cardiología");
        cita = new Cita(1, paciente, medico, fecha);
        System.out.println("Cita agendada: " + cita.getFecha());

        return cita;
    }

    @DeleteMapping("/{citaId}")
    public String cancelarCita(@PathVariable int citaId) {
        // Lógica para cancelar o reprogramar cita
        System.out.println("¿Desea cancelar o reprogramar la cita?");
        System.out.println("1. Cancelar");
        System.out.println("2. Reprogramar");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        switch (opcion) {
            case 1:
                // Simular cancelación de cita
                System.out.println("Cita con ID " + citaId + " cancelada.");
                break;
            case 2:
                // Simular reprogramación de cita
                System.out.println("Ingrese la nueva fecha de la cita (dd/MM/yyyy):");
                String nuevaFecha = scanner.nextLine();
                System.out.println("Cita con ID " + citaId + " reprogramada a: " + nuevaFecha);
                break;
            default:
                System.out.println("Opción no válida");
        }

        return "Cita procesada con éxito.";
    }
}
