/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.Implementations;

import patternsproject.DAOs.CollectionDAO;
import patternsproject.Models.OurCollection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author highv
 */
public class CollectionDAOImpl implements CollectionDAO{

    private final String dbUrl = "jdbc:sqlite:collections.db";
    private OurCollection extractCollectionFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("collectionId");
        String name = rs.getString("collectionTitle");
        int creator = rs.getInt("collectionCreatorId");
        return new OurCollection(id, name, creator);
    }
    @Override
    public Optional<OurCollection> getCollectionById(int id) {
        String sql = "SELECT * FROM collections WEHERE collectionId = ?";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return Optional.of(extractCollectionFromResultSet(rs));
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<OurCollection> getCollectionsByTitle(String title) {
        List<OurCollection> collectionsWithMatchingTitle = new ArrayList<>();
        String sql = "SELECT * FROM collections WHERE collectionId = ?";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()){
                collectionsWithMatchingTitle.add(extractCollectionFromResultSet(rs));
            }
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return collectionsWithMatchingTitle;
    }

    @Override
    public List<OurCollection> getAllCollections() {
        List<OurCollection> collections = new ArrayList<>();
        String sql = "SELECT * FROM collection";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                collections.add(extractCollectionFromResultSet(rs));
            }
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return collections;
    }

    @Override
    public void createCollection(OurCollection collection) {
        String sql = "INSERT INTO collection VALUES(?,?,?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, collection.getId());
            pstmt.setString(2, collection.getName());
            pstmt.setInt(3, collection.getCreatorId());
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void updateCollection(OurCollection collection) {
        String sql = "UPDATE collection SET collectionTitle = ?, collectionCreatorId= ? WHERE collectionId = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, collection.getName());
            pstmt.setInt(2, collection.getCreatorId());
            pstmt.setInt(3, collection.getId());
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteCollection(int id) {
        String sql = "DELETE FROM collection WHERE collectionId=?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }
}