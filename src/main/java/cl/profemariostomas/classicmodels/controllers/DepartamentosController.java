package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.DepartamentoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartamentosController {

    public static ControllerResponse insert(DepartamentoModel departamento) {
        String SQL_INSERT = "INSERT INTO departamentos (id_depto, nombre_depto, centro_costo) VALUES (?, ?, ?);";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, departamento.getIdDepto());
            pstmt.setString(2, departamento.getNombreDepto());
            pstmt.setString(3, departamento.getCentroCosto());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Departamento " + departamento.getNombreDepto() + " guardado!");
            }
            return new ControllerResponse(false, "No se insertó el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al guardar: " + e.getMessage());
        }
    }

    public static ControllerResponse update(DepartamentoModel departamento) {
        String SQL_UPDATE = "UPDATE departamentos SET nombre_depto = ?, centro_costo = ? WHERE id_depto = ?;";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, departamento.getNombreDepto());
            pstmt.setString(2, departamento.getCentroCosto());
            pstmt.setInt(3, departamento.getIdDepto());

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {
                return new ControllerResponse(true, "Departamento " + departamento.getIdDepto() + " actualizado!");
            }
            return new ControllerResponse(false, "No se encontró el registro");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error al actualizar: " + e.getMessage());
        }
    }

    public static ArrayList<DepartamentoModel> select() {
        String SQL_SELECT = "SELECT * FROM departamentos;";
        ArrayList<DepartamentoModel> departamentos = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {

            ResultSet records = pstmt.executeQuery();

            while (records.next()) {
                departamentos.add(
                    new DepartamentoModel(
                        records.getInt("id_depto"),
                        records.getString("nombre_depto"),
                        records.getString("centro_costo")
                    )
                );
            }

            return departamentos;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
