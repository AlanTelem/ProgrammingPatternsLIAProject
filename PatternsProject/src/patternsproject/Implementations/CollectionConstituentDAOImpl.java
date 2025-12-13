/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.Implementations;

import patternsproject.DAOs.CollectionConstituentDAO;
import patternsproject.Models.CollectionConstituent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author highv
 */
public class CollectionConstituentDAOImpl implements CollectionConstituentDAO{
    private String dbUrl="jdbc:sqlite:collections.db";
    @Override
    public void createConstituent(CollectionConstituent constituent) {
        String sql = "INSERT INTO collectionConstituent VALUES(?,?,?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, constituent.getConstituentId());
            pstmt.setInt(2, constituent.getCollectionId());
            pstmt.setInt(3, constituent.getCollectibleId());
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteConstituent(int id) {
        String sql = "DELETE FROM collectionConstituent WHERE id=?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }
    
}
