/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public class OurCollection {
    private static int collectionIdCounter=1;
    
    private int id;
    private String name;
    private int creatorId;

    public OurCollection(int id, String name, int creatorId) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
    }

    public OurCollection(String name, int creatorId) {
        this.name = name;
        this.creatorId = creatorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        return "Collection{" + "id=" + id + ", name=" + name + ", creatorId=" + creatorId + '}';
    }
}