/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.Models;

/**
 *
 * @author highv
 */
public class User {
    private static int userIdCounter = 1;
    
    private final int userId;
    private final String userName;
    private final String encryptedPassword;

    public User(String userName, String password) {
        userId = userIdCounter++;
        this.userName = userName;
        encryptedPassword = password + "encrypted";
    }

    public User(int userId, String userName, String encryptedPassword) {
        this.userId = userId;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword+"encrypted";
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", encryptedPassword=" + encryptedPassword + '}';
    }
}