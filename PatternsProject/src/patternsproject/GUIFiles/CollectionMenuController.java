/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CollectionMenuController implements Initializable{

    @FXML
    private Button addItemBtn;

    @FXML
    private ListView<?> collectionListView;

    @FXML
    private Button exitBtn;

    @FXML
    private Label titleLabel;
    
    String collectionName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var i18n = I18nManager.get();

        addItemBtn.textProperty().bind(i18n.bind("collectionMenu.addItem"));
        exitBtn.textProperty().bind(i18n.bind("common.exit"));
        titleLabel.textProperty().bind(i18n.bind("collectionMenu.title"));
        titleLabel.textProperty().bind(I18nManager.get().bind("collectionMenu.titleFormat", collectionName));
        
        FxAutoSize.install(titleLabel);
    }    
}

