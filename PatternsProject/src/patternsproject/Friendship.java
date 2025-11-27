/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public class Friendship {
    private int friendshipId, userId, friendId; 

    public Friendship(int friendshipId, int userId, int friendId) {
        this.friendshipId = friendshipId;
        this.userId = userId;
        this.friendId = friendId;
    }

    public int getFriendshipId() {
        return friendshipId;
    }

    public int getUserId() {
        return userId;
    }

    public int getFriendId() {
        return friendId;
    }

    @Override
    public String toString() {
        return "Friendship{" + "friendshipId=" + friendshipId + ", userId=" + userId + ", friendId=" + friendId + '}';
    }
    
}
