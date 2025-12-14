/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import patternsproject.I18nManager;
import patternsproject.FxAutoSize;
import patternsproject.Implementations.CollectionDAOImpl;
import patternsproject.Models.OurCollection;

public class CollectionMenuController implements Initializable{

    @FXML
    private Button addItemBtn;

    @FXML
    private ListView<OurCollection> collectionListView;

    @FXML
    private Button exitBtn;

    @FXML
    private Label titleLabel;
    
    String collectionName;
    private CollectionDAOImpl collections = new CollectionDAOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var i18n = I18nManager.get();

        addItemBtn.textProperty().bind(i18n.bind("collectionMenu.addItem"));
        exitBtn.textProperty().bind(i18n.bind("common.exit"));
        titleLabel.textProperty().bind(i18n.bind("collectionMenu.title"));
        titleLabel.textProperty().bind(I18nManager.get().bind("collectionMenu.titleFormat", collectionName));
        
        FxAutoSize.install(titleLabel);
    }

    private void loadCollections(ActionEvent event){
        ObservableList<OurCollection> listOfCollections = FXCollections.observableList(collections.getAllCollections());
        collectionListView.setItems(listOfCollections);
    }
}

