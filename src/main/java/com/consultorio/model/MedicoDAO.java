package com.consultorio.model;

import java.sql.*;

public class MedicoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/consultorio";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public boolean registrarMedico(Medico medico) {
        String query = "INSERT INTO medicos (nombre, especialidad) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getEspecialidad());
            int result = stmt.executeUpdate();
            return result > 0; // Si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Medico obtenerMedicoPorId(int id) {
        String query = "SELECT * FROM medicos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Medico(rs.getInt("id"), rs.getString("nombre"), rs.getString("especialidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
