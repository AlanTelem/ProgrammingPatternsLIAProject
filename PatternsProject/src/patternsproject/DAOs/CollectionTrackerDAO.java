/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patternsproject.DAOs;

import patternsproject.Models.CollectionTracker;

/**
 *
 * @author highv
 */
public interface CollectionTrackerDAO {
    void createTracker(CollectionTracker tracker);
    void deleteTracker(int id);
}
