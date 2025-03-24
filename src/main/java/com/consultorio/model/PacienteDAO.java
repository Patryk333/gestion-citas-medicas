package com.consultorio.model;

import java.sql.*;

public class PacienteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/consultorio";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public boolean registrarPaciente(Paciente paciente) {
        String query = "INSERT INTO pacientes (nombre, telefono) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getTelefono());
            int result = stmt.executeUpdate();
            return result > 0; // Si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Paciente obtenerPacientePorId(int id) {
        String query = "SELECT * FROM pacientes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Paciente(rs.getInt("id"), rs.getString("nombre"), rs.getString("telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
