package com.consultorio.model;

import java.sql.*;

public class CitaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/consultorio";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public boolean agregarCita(Cita cita) {
        String query = "INSERT INTO citas (paciente_id, medico_id, fecha) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cita.getPaciente().getId());
            stmt.setInt(2, cita.getMedico().getId());
            stmt.setString(3, cita.getFecha());
            int result = stmt.executeUpdate();
            return result > 0; // Si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cita obtenerCitaPorId(int id) {
        String query = "SELECT c.id, p.nombre AS paciente_nombre, m.nombre AS medico_nombre, c.fecha " +
                "FROM citas c " +
                "JOIN pacientes p ON c.paciente_id = p.id " +
                "JOIN medicos m ON c.medico_id = m.id " +
                "WHERE c.id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paciente paciente = new Paciente(rs.getInt("paciente_id"), rs.getString("paciente_nombre"), null);
                Medico medico = new Medico(rs.getInt("medico_id"), rs.getString("medico_nombre"), null);
                return new Cita(rs.getInt("id"), paciente, medico, rs.getString("fecha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
