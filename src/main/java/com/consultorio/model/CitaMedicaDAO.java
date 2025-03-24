package com.consultorio.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaMedicaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/consultorio";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    // Lista de citas médicas
    List<CitaMedica> citas = new ArrayList<>();

    // Obtener todas las citas médicas
    public List<CitaMedica> obtenerTodasLasCitas() {
        String query = "SELECT c.id, p.nombre AS paciente_nombre, p.id AS paciente_id, m.nombre AS medico_nombre, m.id AS medico_id, c.fecha " +
                "FROM citas c " +
                "JOIN pacientes p ON c.paciente_id = p.id " +
                "JOIN medicos m ON c.medico_id = m.id";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Crear objeto CitaMedica con la información de la consulta
                CitaMedica cita = new CitaMedica(
                        rs.getInt("id"),
                        rs.getString("paciente_nombre"),
                        rs.getString("medico_nombre"),
                        rs.getString("fecha"),
                        rs.getInt("paciente_id"),
                        rs.getInt("medico_id")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    // Obtener todas las citas de un paciente específico
    public List<CitaMedica> obtenerCitasPorPaciente(int pacienteId) {
        List<CitaMedica> citasPorPaciente = new ArrayList<>();
        String query = "SELECT c.id, p.nombre AS paciente_nombre, m.nombre AS medico_nombre, c.fecha, c.paciente_id, c.medico_id " +
                "FROM citas c " +
                "JOIN pacientes p ON c.paciente_id = p.id " +
                "JOIN medicos m ON c.medico_id = m.id " +
                "WHERE p.id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pacienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CitaMedica cita = new CitaMedica(
                            rs.getInt("id"),
                            rs.getString("paciente_nombre"),
                            rs.getString("medico_nombre"),
                            rs.getString("fecha"),
                            rs.getInt("paciente_id"),
                            rs.getInt("medico_id")
                    );
                    citasPorPaciente.add(cita);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citasPorPaciente;
    }

    // Registrar una nueva cita
    public void agendarCita(CitaMedica cita) {
        String query = "INSERT INTO citas (paciente_id, medico_id, fecha) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cita.getPacienteId());
            stmt.setInt(2, cita.getMedicoId());
            stmt.setString(3, cita.getFecha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cancelar una cita
    public void cancelarCita(int citaId) {
        String query = "DELETE FROM citas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, citaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Reprogramar una cita
    public void reprogramarCita(int citaId, CitaMedica nuevaCita) {
        String query = "UPDATE citas SET paciente_id = ?, medico_id = ?, fecha = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nuevaCita.getPacienteId());
            stmt.setInt(2, nuevaCita.getMedicoId());
            stmt.setString(3, nuevaCita.getFecha());
            stmt.setInt(4, citaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mostrar todas las citas (console output)
    public void mostrarCitas() {
        for (CitaMedica cita : citas) {
            System.out.println(cita);
        }
    }
}
