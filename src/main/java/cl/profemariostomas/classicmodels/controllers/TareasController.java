package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.TareaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TareasController {

    public static ControllerResponse insert(TareaModel tarea) {
        String SQL_INSERT = "INSERT INTO tareas (id_tarea, id_empleado_asignado, estado, horas_estimadas) VALUES (?, ?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, tarea.getIdTarea());
            pstmt.setInt(2, tarea.getIdEmpleadoAsignado());
            pstmt.setString(3, tarea.getEstado());
            pstmt.setInt(4, tarea.getHorasEstimadas());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Tarea " + tarea.getIdTarea() + " guardada!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(TareaModel tarea) {
        String SQL_UPDATE = "UPDATE tareas SET id_empleado_asignado = ?, estado = ?, horas_estimadas = ? WHERE id_tarea = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setInt(1, tarea.getIdEmpleadoAsignado());
            pstmt.setString(2, tarea.getEstado());
            pstmt.setInt(3, tarea.getHorasEstimadas());
            pstmt.setInt(4, tarea.getIdTarea());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Tarea " + tarea.getIdTarea() + " actualizada!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<TareaModel> select() {
        String SQL_SELECT = "SELECT * FROM tareas;";
        ArrayList<TareaModel> tareas = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                tareas.add(
                    new TareaModel(
                        records.getInt("id_tarea"),
                        records.getInt("id_empleado_asignado"),
                        records.getString("estado"),
                        records.getInt("horas_estimadas")
                    )
                );
            }

            return tareas;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
