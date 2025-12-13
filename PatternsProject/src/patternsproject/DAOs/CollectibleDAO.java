/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.DAOs;

import patternsproject.Models.OurCollectible;

/**
 *
 * @author highv
 */
public interface CollectibleDAO {
    java.util.Optional<OurCollectible> getCollectibleById(int id);
    void createCollectible(OurCollectible collectible);
    void updateCollectible(OurCollectible collectible);
    void deleteCollectible(int id);
}