/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public interface FriendshipDAO {
    void createFriendship(Friendship friend);
    void deleteFriendship(int id);
    java.util.List<User> getFriends(int userId);
}
