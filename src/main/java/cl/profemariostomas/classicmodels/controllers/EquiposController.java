package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.EquipoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquiposController {

    public static ControllerResponse insert(EquipoModel equipo) {
        String SQL_INSERT = "INSERT INTO EQUIPOS (descripcion, numero_serie, aula_asignada_id) VALUES (?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, equipo.getDescripcion());
            pstmt.setString(2, equipo.getNumeroSerie());
            pstmt.setInt(3, equipo.getAulaAsignadaId());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Equipo " + equipo.getDescripcion() + " guardado!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(EquipoModel equipo) {
        String SQL_UPDATE = "UPDATE EQUIPOS SET descripcion = ?, numero_serie = ?, aula_asignada_id = ? WHERE equipo_id = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, equipo.getDescripcion());
            pstmt.setString(2, equipo.getNumeroSerie());
            pstmt.setInt(3, equipo.getAulaAsignadaId());
            pstmt.setInt(4, equipo.getEquipoId());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Equipo " + equipo.getEquipoId() + " actualizado!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<EquipoModel> select() {
        String SQL_SELECT = "SELECT e.*, a.nombre AS aula_nombre FROM EQUIPOS e JOIN AULAS a ON e.aula_asignada_id = a.aula_id;";
        ArrayList<EquipoModel> equipos = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                equipos.add(
                    new EquipoModel(
                        records.getInt("equipo_id"),
                        records.getString("descripcion"),
                        records.getString("numero_serie"),
                        records.getInt("aula_asignada_id"),
                        records.getString("aula_nombre")
                    )
                );
            }

            return equipos;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
