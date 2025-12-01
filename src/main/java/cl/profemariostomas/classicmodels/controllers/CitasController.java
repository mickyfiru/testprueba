package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.CitaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CitasController {

    public static ControllerResponse insert(CitaModel cita) {
        String SQL_INSERT = "INSERT INTO Citas (id_cita, id_medico, id_paciente, fecha_cita, estado, motivo) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, cita.getIdCita());
            pstmt.setInt(2, cita.getIdMedico());
            pstmt.setInt(3, cita.getIdPaciente());
            pstmt.setTimestamp(4, cita.getFechaCita());
            pstmt.setString(5, cita.getEstado());
            pstmt.setString(6, cita.getMotivo());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Cita " + cita.getIdCita() + " guardada!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(CitaModel cita) {
        String SQL_UPDATE = "UPDATE Citas SET id_medico = ?, id_paciente = ?, fecha_cita = ?, estado = ?, motivo = ? WHERE id_cita = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setInt(1, cita.getIdMedico());
            pstmt.setInt(2, cita.getIdPaciente());
            pstmt.setTimestamp(3, cita.getFechaCita());
            pstmt.setString(4, cita.getEstado());
            pstmt.setString(5, cita.getMotivo());
            pstmt.setInt(6, cita.getIdCita());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Cita " + cita.getIdCita() + " actualizada!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<CitaModel> select() {
        String SQL_SELECT = "SELECT * FROM Citas;";
        ArrayList<CitaModel> citas = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                citas.add(
                    new CitaModel(
                        records.getInt("id_cita"),
                        records.getInt("id_medico"),
                        records.getInt("id_paciente"),
                        records.getTimestamp("fecha_cita"),
                        records.getString("estado"),
                        records.getString("motivo")
                    )
                );
            }

            return citas;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
