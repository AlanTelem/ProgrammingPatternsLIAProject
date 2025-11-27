/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public class CollectionTracker {
    private int trackerId, collectionId, userId;

    public CollectionTracker(int trackerId, int collectionId, int userId) {
        this.trackerId = trackerId;
        this.collectionId = collectionId;
        this.userId = userId;
    }

    public int getTrackerId() {
        return trackerId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CollectionTracker{" + "trackerId=" + trackerId + ", collectionId=" + collectionId + ", userId=" + userId + '}';
    }
}