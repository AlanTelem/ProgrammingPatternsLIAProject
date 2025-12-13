/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.Implementations;

import patternsproject.DAOs.CollectedItemDAO;
import patternsproject.Models.CollectedItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author highv
 */
public class CollectedItemDAOImpl implements CollectedItemDAO{
    private final String dbUrl="jdbc:sqlite:collections.db";
    
    @Override
    public void createCollected(CollectedItem item) {
        String sql = "INSERT INTO collectedItem VALUES(?,?,?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, item.getCollectedId());
            pstmt.setInt(2, item.getUserId());
            pstmt.setInt(3, item.getCollectibleId());
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteCollected(int id) {
        String sql = "DELETE FROM collectedItem WHERE id=?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }
}