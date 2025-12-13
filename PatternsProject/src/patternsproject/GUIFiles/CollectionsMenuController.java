/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import patternsproject.Implementations.CollectionDAOImpl;
import patternsproject.Models.OurCollection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author 6119391
 */
public class CollectionsMenuController implements Initializable {

    @FXML
    private Label listLbl;
    @FXML
    private ListView<String> allCollectionsListView;
    @FXML
    private Button addNewBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button deleteBtn;
    
    private CollectionDAOImpl collectionSql = new CollectionDAOImpl();
    
    private ObservableList<String> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<OurCollection> list = collectionSql.getAllCollections();
        for (OurCollection ourCollection : list) {
            String stringRep = ourCollection.getName();
            data.add(stringRep);
        }
        allCollectionsListView.setItems(data);
    }    
    
}
