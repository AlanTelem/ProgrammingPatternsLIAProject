/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patternsproject;

/**
 *
 * @author highv
 */
public interface CollectionDAO {
    java.util.Optional<OurCollection> getCollectionById(int id);
    java.util.List<OurCollection> getCollectionsByTitle(String title);
    java.util.List<OurCollection> getAllCollections();
    void createCollection(OurCollection collection);
    void updateCollection(OurCollection collection);
    void deleteCollection(int id);
}