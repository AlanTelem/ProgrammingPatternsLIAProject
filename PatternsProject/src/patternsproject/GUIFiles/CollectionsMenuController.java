/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import java.io.IOException;
import patternsproject.Implementations.CollectionDAOImpl;
import patternsproject.Models.OurCollection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import patternsproject.I18nManager;
import patternsproject.FxAutoSize;


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
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var i18n = I18nManager.get();

        listLbl.textProperty().bind(i18n.bind("collectionsMenu.title"));
        addNewBtn.textProperty().bind(i18n.bind("collectionsMenu.addNew"));
        deleteBtn.textProperty().bind(i18n.bind("collectionsMenu.delete"));
        exitBtn.textProperty().bind(i18n.bind("common.exit"));

        List<OurCollection> list = collectionSql.getAllCollections();
        for (OurCollection ourCollection : list) {
            String stringRep = ourCollection.getName();
            data.add(stringRep);
        }
        allCollectionsListView.setItems(data);
        
        FxAutoSize.install(listLbl);
    }
    
    public void exit(ActionEvent event){
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/patternsproject/GUIFiles/UserMenu.FXML"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collection Application");
        stage.show();
        
        parentStage.close();
    }
    
    public void deleteCollection(ActionEvent event){
        String collectionName = allCollectionsListView.selectionModelProperty().get().getSelectedItem();
    }
}