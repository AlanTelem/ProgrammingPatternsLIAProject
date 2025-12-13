/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patternsproject.DAOs;

import patternsproject.Models.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author highv
 */
public interface UserDAO {
    Optional<User> getUserById(int id);
    List<User> getAllUsers();
    String getUserByUsername(String username);
    String getPasswordByUsername(String username);
    String getUsernameById(int id);
    int getIdByUser(String username);
    boolean userExists(String username);
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
