/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author Lucas
 */
public class UserSession {
    private static UserSession instance;
    
    private int userId;

    private UserSession(int userId) {
        this.userId = userId;
    }
    
    public static void setInstance(int userId) {
        if (instance == null){
            instance = new UserSession(userId);
        }
    }
    
    public static UserSession getInstance(){
        return instance;
    }
    
    public static void logOutInstance(){
        instance = null;
    }

    public int getUserId() {
        return userId;
    }
}
