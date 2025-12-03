/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author highv
 */
public class UserDAOImpl implements UserDAO{
    private final String dbUrl = "jdbc:sqlite:collections.db";
    
    private User extractUserFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("userId");
        String name = rs.getString("userName");
        String password = rs.getString("password");
        return new User(id, name, password);
    }
    @Override
    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return Optional.of(extractUserFromResultSet(rs));
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                Statement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return users;
    }

    @Override
    public String getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE userName = ?";
        String result = null;
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                result = rs.getString("userName");
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return result;
    }

    @Override
    public String getUsernameById(int id) {
        String sql = "SELECT userName FROM users WHERE userId = ?";
        String result = null;
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                result = rs.getString("userName");
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return result;
    }
    
    
    
    @Override
    public String getPasswordByUsername(String username) {
        String sql = "SELECT passwordEncrypted FROM users WHERE userName = ?";
        String result = null;
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                result = rs.getString("passwordEncrypted");
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return result;
    }
    
    @Override
    public int getIdByUser(String username) {
        String sql = "SELECT userId FROM users WHERE userName = ?";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                result = rs.getInt("userId");
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return result;
    }
    
        @Override
    public boolean userExists(String username) {
        String sql = "SELECT * FROM users WHERE userName = ?";
        boolean result = false;
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                result = true;
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
        return result;
    }

    @Override
    public void registerUser(User user) {
        String sql = "INSERT INTO Users (userName, passwordEncrypted) VALUES(?,?)";
        try(Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEncryptedPassword());
            
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, password = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEncryptedPassword());
            pstmt.setInt(3, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
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
