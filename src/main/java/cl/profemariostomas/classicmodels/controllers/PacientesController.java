package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.PacienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacientesController {

    public static ControllerResponse insert(PacienteModel paciente) {
        String SQL_INSERT = "INSERT INTO Pacientes (id_paciente, nombre, apellido, rut, fecha_nacimiento) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, paciente.getIdPaciente());
            pstmt.setString(2, paciente.getNombre());
            pstmt.setString(3, paciente.getApellido());
            pstmt.setString(4, paciente.getRut());
            pstmt.setDate(5, paciente.getFechaNacimiento());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Paciente " + paciente.getNombre() + " guardado!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(PacienteModel paciente) {
        String SQL_UPDATE = "UPDATE Pacientes SET nombre = ?, apellido = ?, rut = ?, fecha_nacimiento = ? WHERE id_paciente = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellido());
            pstmt.setString(3, paciente.getRut());
            pstmt.setDate(4, paciente.getFechaNacimiento());
            pstmt.setInt(5, paciente.getIdPaciente());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Paciente " + paciente.getIdPaciente() + " actualizado!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<PacienteModel> select() {
        String SQL_SELECT = "SELECT * FROM Pacientes;";
        ArrayList<PacienteModel> pacientes = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                pacientes.add(
                    new PacienteModel(
                        records.getInt("id_paciente"),
                        records.getString("nombre"),
                        records.getString("apellido"),
                        records.getString("rut"),
                        records.getDate("fecha_nacimiento")
                    )
                );
            }

            return pacientes;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
