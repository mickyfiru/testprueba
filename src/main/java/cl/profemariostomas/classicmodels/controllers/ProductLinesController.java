/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.profemariostomas.classicmodels.controllers;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.MySQLConnection;
import cl.profemariostomas.classicmodels.models.ProductLinesModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author luk0s
 */
public class ProductLinesController {

    public static ControllerResponse insert(ProductLinesModel plm) {
        String SQL_INSERT = "INSERT INTO `productlines` (`productLine`, `textDescription`, `htmlDescription`, `image`) values (?, ?, ?, null);";
        
        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
            
            pstmt.setString(1, plm.getProductLine());
            pstmt.setString(2, plm.getTextDescription());
            pstmt.setString(3, plm.getHtmlDescription());
            
            int rowCount = pstmt.executeUpdate();
            
            if (rowCount > 0) {
                return new ControllerResponse(true, "ProductLine: " + plm.getProductLine() + " saved!");
            }
            return new ControllerResponse(false, "wat ¿?");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error when saving: " + e.getMessage());
        }
    }
    
    public static ControllerResponse update(ProductLinesModel plm) {
        String SQL_UPDATE = "UPDATE `productlines` SET `textDescription` = ?, `htmlDescription` = ?, `image` = ? WHERE `productLine` = ?;";
        
        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {
            
            pstmt.setString(1, plm.getTextDescription());
            pstmt.setString(2, plm.getHtmlDescription());
            pstmt.setString(3, plm.getImage());
            pstmt.setString(4, plm.getProductLine());
            
            int rowCount = pstmt.executeUpdate();
            
            if (rowCount > 0) {
                return new ControllerResponse(true, "ProductLine: " + plm.getProductLine() + " updated!");
            }
            return new ControllerResponse(false, "wat ¿?");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error when updating: " + e.getMessage());
        }
    }
    
    public static ControllerResponse delete(ProductLinesModel plm) {
        String SQL_DELETE = "DELETE FROM `productlines` WHERE `productLine` = ?;";
        
        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {
            
            pstmt.setString(1, plm.getProductLine());
            
            int rowCount = pstmt.executeUpdate();
            
            if (rowCount > 0) {
                return new ControllerResponse(true, "ProductLine: " + plm.getProductLine() + " deleted!");
            }
            return new ControllerResponse(false, "wat ¿?");
        } catch (SQLException e) {
            return new ControllerResponse(false, "Error when deleting: " + e.getMessage());
        }
    }
    
    public static ArrayList<ProductLinesModel> select() {
        String SQL_SELECT = "SELECT * FROM `productlines`;";
        ArrayList<ProductLinesModel> lines = new ArrayList<>();
                
        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
            
            ResultSet records = pstmt.executeQuery();
            
            while(records.next()) {
                lines.add(
                    new ProductLinesModel(
                        records.getString("productLine"),
                        records.getString("textDescription"),
                        records.getString("htmlDescription"),
                        records.getString("image")
                    )
                );
            }
            
            return lines;
        } catch (SQLException e) {
            System.out.println("Cant get data: " + e.getMessage());
            return null;
        }
    }
}
