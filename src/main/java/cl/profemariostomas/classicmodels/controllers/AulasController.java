package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.AulaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AulasController {

    public static ControllerResponse insert(AulaModel aula) {
        String SQL_INSERT = "INSERT INTO AULAS (nombre, capacidad) VALUES (?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, aula.getNombre());
            pstmt.setInt(2, aula.getCapacidad());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Aula " + aula.getNombre() + " guardada!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(AulaModel aula) {
        String SQL_UPDATE = "UPDATE AULAS SET nombre = ?, capacidad = ? WHERE aula_id = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, aula.getNombre());
            pstmt.setInt(2, aula.getCapacidad());
            pstmt.setInt(3, aula.getAulaId());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Aula " + aula.getAulaId() + " actualizada!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<AulaModel> select() {
        String SQL_SELECT = "SELECT * FROM AULAS;";
        ArrayList<AulaModel> aulas = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                aulas.add(
                    new AulaModel(
                        records.getInt("aula_id"),
                        records.getString("nombre"),
                        records.getInt("capacidad")
                    )
                );
            }

            return aulas;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
