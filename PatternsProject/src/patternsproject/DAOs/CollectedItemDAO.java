/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package patternsproject.DAOs;

import patternsproject.Models.CollectedItem;

/**
 *
 * @author highv
 */
public interface CollectedItemDAO {
     void createCollected(CollectedItem item);
     void deleteCollected(int id);
}
