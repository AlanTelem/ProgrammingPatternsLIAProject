/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author highv
 */
public class CollectionTrackerDAOImpl implements CollectionTrackerDAO{
    private String dbUrl = "jdbc:sqlite:collections.db";
    
    @Override
    public void createTracker(CollectionTracker tracker) {
        String sql = "INSERT INTO collectionTracker VALUES(?,?,?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, tracker.getTrackerId());
            pstmt.setInt(2, tracker.getCollectionId());
            pstmt.setInt(3, tracker.getUserId());
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteTracker(int id) {
        String sql = "DELETE FROM collectionTracker WHERE id=?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
}
