package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.MedicoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicosController {

    public static ControllerResponse insert(MedicoModel medico) {
        String SQL_INSERT = "INSERT INTO Medicos (id_medico, nombre, apellido, numero_licencia, id_especialidad) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, medico.getIdMedico());
            pstmt.setString(2, medico.getNombre());
            pstmt.setString(3, medico.getApellido());
            pstmt.setString(4, medico.getNumeroLicencia());
            pstmt.setInt(5, medico.getIdEspecialidad());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Médico " + medico.getNombre() + " guardado!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(MedicoModel medico) {
        String SQL_UPDATE = "UPDATE Medicos SET nombre = ?, apellido = ?, numero_licencia = ?, id_especialidad = ? WHERE id_medico = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, medico.getNombre());
            pstmt.setString(2, medico.getApellido());
            pstmt.setString(3, medico.getNumeroLicencia());
            pstmt.setInt(4, medico.getIdEspecialidad());
            pstmt.setInt(5, medico.getIdMedico());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Médico " + medico.getIdMedico() + " actualizado!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<MedicoModel> select() {
        String SQL_SELECT = "SELECT * FROM Medicos;";
        ArrayList<MedicoModel> medicos = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                medicos.add(
                    new MedicoModel(
                        records.getInt("id_medico"),
                        records.getString("nombre"),
                        records.getString("apellido"),
                        records.getString("numero_licencia"),
                        records.getInt("id_especialidad")
                    )
                );
            }

            return medicos;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
