/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author highv
 */
public class CollectibleDAOImpl implements CollectibleDAO{

    private final String dbUrl = "jdbc:sqlite:collections.db";
    
    private OurCollectible extractCollectibleFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("collectionId");
        String name = rs.getString("collectionTitle");
        Image thumbnail = (Image)rs.getBlob("thumbnail");
        return new OurCollectible(id, name, thumbnail);
    }
    @Override
    public Optional<OurCollectible> getCollectibleById(int id) {
        String sql = "SELECT * FROM collectibles WEHERE collectibleId = ?";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return Optional.of(extractCollectibleFromResultSet(rs));
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void createCollectible(OurCollectible collectible) {
        String sql = "INSERT INTO collectibles VALUES(?,?,?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, collectible.getCollectibleId());
            pstmt.setString(2, collectible.getCollectibleName());
            pstmt.setBlob(3, (Blob)collectible.getThumbnail());
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void updateCollectible(OurCollectible collectible) {
        String sql = "UPDATE collectibles SET name = ?, thumbnail = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, collectible.getCollectibleName());
            pstmt.setBlob(2, (Blob)collectible.getThumbnail());
            pstmt.setInt(3, collectible.getCollectibleId());
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteCollectible(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

}
