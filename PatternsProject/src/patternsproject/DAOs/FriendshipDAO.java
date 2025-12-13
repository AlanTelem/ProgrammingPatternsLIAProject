/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patternsproject.DAOs;

import patternsproject.Models.User;
import patternsproject.Models.Friendship;
import java.util.List;
import java.util.Map;

/**
 *
 * @author highv
 */
public interface FriendshipDAO {
    void createFriendship(Friendship friend);
    void deleteFriendship(int id);
    java.util.List<User> getFriends(int userId);
    List<Map<String, Object>> getFriendsCollections(int userId);
    List<String> getFriendsName(int userId);
}
