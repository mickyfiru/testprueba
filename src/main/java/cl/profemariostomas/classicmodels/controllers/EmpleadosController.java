package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.EmpleadoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadosController {

    public static ControllerResponse insert(EmpleadoModel empleado) {
        String SQL_INSERT = "INSERT INTO empleados (id_empleado, nombre, apellido, cargo, id_depto) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, empleado.getIdEmpleado());
            pstmt.setString(2, empleado.getNombre());
            pstmt.setString(3, empleado.getApellido());
            pstmt.setString(4, empleado.getCargo());
            pstmt.setInt(5, empleado.getIdDepto());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Empleado " + empleado.getNombre() + " guardado!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(EmpleadoModel empleado) {
        String SQL_UPDATE = "UPDATE empleados SET nombre = ?, apellido = ?, cargo = ?, id_depto = ? WHERE id_empleado = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellido());
            pstmt.setString(3, empleado.getCargo());
            pstmt.setInt(4, empleado.getIdDepto());
            pstmt.setInt(5, empleado.getIdEmpleado());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Empleado " + empleado.getIdEmpleado() + " actualizado!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<EmpleadoModel> select() {
        String SQL_SELECT = "SELECT * FROM empleados;";
        ArrayList<EmpleadoModel> empleados = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                empleados.add(
                    new EmpleadoModel(
                        records.getInt("id_empleado"),
                        records.getString("nombre"),
                        records.getString("apellido"),
                        records.getString("cargo"),
                        records.getInt("id_depto")
                    )
                );
            }

            return empleados;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
