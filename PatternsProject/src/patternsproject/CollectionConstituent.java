/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public class CollectionConstituent {
    private int constituentId, collectionId, collectibleId;

    public CollectionConstituent(int constituentId, int collectionId, int collectibleId) {
        this.constituentId = constituentId;
        this.collectionId = collectionId;
        this.collectibleId = collectibleId;
    }

    public int getConstituentId() {
        return constituentId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getCollectibleId() {
        return collectibleId;
    }

    @Override
    public String toString() {
        return "CollectionConstituent{" + "constituentId=" + constituentId + ", collectionId=" + collectionId + ", collectibleId=" + collectibleId + '}';
    }
}
