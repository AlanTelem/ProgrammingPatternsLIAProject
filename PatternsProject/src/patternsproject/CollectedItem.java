/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public class CollectedItem {
    private int collectedId, userId, collectibleId;

    public CollectedItem(int collectedId, int userId, int collectibleId) {
        this.collectedId = collectedId;
        this.userId = userId;
        this.collectibleId = collectibleId;
    }

    public int getCollectedId() {
        return collectedId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCollectibleId() {
        return collectibleId;
    }

    @Override
    public String toString() {
        return "CollectedItem{" + "collectedId=" + collectedId + ", userId=" + userId + ", collectibleId=" + collectibleId + '}';
    }
}
