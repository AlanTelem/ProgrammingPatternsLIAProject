/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author highv
 */
public class FriendshipDAOImpl implements FriendshipDAO{
    private String dbUrl = "jdbc:sqlite:collections.db";
    @Override
    public void createFriendship(Friendship friendship) {
        String sql = "INSERT INTO Friendships (userId, friendId) VALUES(?, ?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, friendship.getUserId());
            pstmt.setInt(2, friendship.getFriendId());
            
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteFriendship(int id) {
        String sql = "DELETE FROM friendships WHERE id=?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public List<User> getFriends(int userId) {
        List<User> friends = new ArrayList<>();
        String sql = "SELECT f.friendshipId, u.userName "
                + "FROM friendships f "
                + "JOIN users u ON f.userId=u.userId "
                + "WHERE f.userId = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery(sql)){
                while(rs.next()){
                    //friends.add();
                    return friends;
                }
            } catch (SQLException sqle){
                System.err.println(sqle.getMessage());
                
            }
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return friends;
    }    
    
    @Override
    public List<Map<String, Object>> getFriendsCollections(int userId){
        List<Map<String, Object>> dataList = new ArrayList<>();
        
        String sql = "SELECT u.userName, col.collectionTitle "
                + "FROM Users u "
                + "JOIN Friendships f ON u.userId = f.friendId "
                + "JOIN CollectionTracker ct ON u.userId = ct.userId "
                + "JOIN Collection col ON ct.collectionId = col.collectionId "
                + "WHERE f.userId = ?";
        
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
                try (ResultSet rs = pstmt.executeQuery()){
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();

                        String friendName = rs.getString("userName");
                        String collection = rs.getString("collectionTitle");

                        row.put("userKey", friendName);
                        row.put("collectionKey", collection);

                        dataList.add(row);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public List<String> getFriendsName(int userId) {
        List<String> names = new ArrayList<>();
        String sql = "SELECT u.userName "
                + "FROM Users u "
                + "JOIN Friendships f ON f.userId=u.userId "
                + "WHERE f.friendId = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    names.add(rs.getString("userName"));
                }
            } catch (SQLException sqle){
                System.err.println(sqle.getMessage());
                
            }
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return names;
    }
}