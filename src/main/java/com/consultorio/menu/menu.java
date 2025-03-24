package com.consultorio.menu;

import java.net.URI;
import java.net.http.*;
import java.util.Scanner;
import java.io.IOException;

public class menu {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        System.out.println("Bienvenido al sistema de consultas médicas");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Registrar nuevo paciente");
        System.out.println("2. Registrar nuevo médico");
        System.out.println("3. Agendar una cita");
        System.out.println("4. Consultar historial de citas de un paciente");
        System.out.println("5. Cancelar o reprogramar una cita");
        System.out.print("Elija una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        // Lógica para elegir qué función ejecutar
        switch (opcion) {
            case 1:
                registrarPaciente(scanner);
                break;
            case 2:
                registrarMedico(scanner);
                break;
            case 3:
                agendarCita(scanner);
                break;
            case 4:
                consultarHistorialDeCitas(scanner);
                break;
            case 5:
                cancelarOReprogramarCita(scanner);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    public static void registrarPaciente(Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Ingrese el nombre del paciente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el teléfono del paciente:");
        String telefono = scanner.nextLine();

        String json = "{ \"nombre\": \"" + nombre + "\", \"telefono\": \"" + telefono + "\" }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/pacientes"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respuesta del servidor: " + response.body());
    }

    public static void registrarMedico(Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Ingrese el nombre del médico:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la especialidad del médico:");
        String especialidad = scanner.nextLine();

        String json = "{ \"nombre\": \"" + nombre + "\", \"especialidad\": \"" + especialidad + "\" }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/medicos"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respuesta del servidor: " + response.body());
    }

    public static void agendarCita(Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Ingrese la fecha de la cita (dd/MM/yyyy):");
        String fecha = scanner.nextLine();

        System.out.println("Ingrese el ID del paciente:");
        int pacienteId = scanner.nextInt();

        System.out.println("Ingrese el ID del médico:");
        int medicoId = scanner.nextInt();

        String json = "{ \"fecha\": \"" + fecha + "\", \"pacienteId\": " + pacienteId + ", \"medicoId\": " + medicoId + " }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/citas"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respuesta del servidor: " + response.body());
    }

    public static void consultarHistorialDeCitas(Scanner scanner) {
        System.out.println("Ingrese el ID del paciente:");
        int pacienteId = scanner.nextInt();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/pacientes/" + pacienteId + "/citas"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Historial de citas: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void cancelarOReprogramarCita(Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Ingrese el ID de la cita:");
        int citaId = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        System.out.println("¿Desea cancelar o reprogramar la cita?");
        System.out.println("1. Cancelar");
        System.out.println("2. Reprogramar");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                // Cancelar cita
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/api/citas/" + citaId))
                        .DELETE()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Cita cancelada: " + response.body());
                break;
            case 2:
                // Reprogramar cita
                System.out.println("Ingrese la nueva fecha de la cita (dd/MM/yyyy):");
                scanner.nextLine();  // Consumir el salto de línea
                String nuevaFecha = scanner.nextLine();
                String json = "{ \"fecha\": \"" + nuevaFecha + "\" }";

                HttpRequest reprogramarRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/api/citas/" + citaId))
                        .header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpResponse<String> reprogramarResponse = client.send(reprogramarRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("Cita reprogramada: " + reprogramarResponse.body());
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
