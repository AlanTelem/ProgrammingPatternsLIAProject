/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.Models;

import java.awt.Image;

/**
 *
 * @author highv
 */
public class OurCollectible {
    private static int collectibleIdCounter=1;
    
    private int collectibleId;
    private String collectibleName;
    private Image thumbnail;

    public OurCollectible(String collectibleName, Image thumbnail) {
        this.collectibleName = collectibleName;
        this.thumbnail = thumbnail;
    }

    public OurCollectible(int collectibleId, String collectibleName, Image thumbnail) {
        this.collectibleId = collectibleId;
        this.collectibleName = collectibleName;
        this.thumbnail = thumbnail;
    }

    public int getCollectibleId() {
        return collectibleId;
    }

    public String getCollectibleName() {
        return collectibleName;
    }

    public Image getThumbnail() {
        return thumbnail;
    }
}
