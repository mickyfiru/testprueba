package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.EspecialidadModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecialidadesController {

    public static ControllerResponse insert(EspecialidadModel especialidad) {
        String SQL_INSERT = "INSERT INTO Especialidades (id_especialidad, nombre_especialidad, codigo) VALUES (?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, especialidad.getIdEspecialidad());
            pstmt.setString(2, especialidad.getNombreEspecialidad());
            pstmt.setString(3, especialidad.getCodigo());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Especialidad " + especialidad.getNombreEspecialidad() + " guardada!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(EspecialidadModel especialidad) {
        String SQL_UPDATE = "UPDATE Especialidades SET nombre_especialidad = ?, codigo = ? WHERE id_especialidad = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, especialidad.getNombreEspecialidad());
            pstmt.setString(2, especialidad.getCodigo());
            pstmt.setInt(3, especialidad.getIdEspecialidad());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Especialidad " + especialidad.getIdEspecialidad() + " actualizada!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<EspecialidadModel> select() {
        String SQL_SELECT = "SELECT * FROM Especialidades;";
        ArrayList<EspecialidadModel> especialidades = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                especialidades.add(
                    new EspecialidadModel(
                        records.getInt("id_especialidad"),
                        records.getString("nombre_especialidad"),
                        records.getString("codigo")
                    )
                );
            }

            return especialidades;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
