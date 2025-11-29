/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author 6119391
 */
public class UserMenuController implements Initializable {

    @FXML
    private Label welcomeLbl;
    @FXML
    private Button friendsBtn;
    @FXML
    private Button collectionsBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private ListView<?> quickViewListView;

    private UserDAO userSQL;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void switchWelcomeText(String text){
        String defaultText = welcomeLbl.getText();
        
        String newText = defaultText + " " + text;
        
        welcomeLbl.setText(newText);
    }
    
    public void switchToCollectionsMenu(ActionEvent event){
       Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("collectionsMenu.FXML"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.show();
        
        parentStage.close();
    }
    
    public void logOut(ActionEvent event){
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInMenu.FXML"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.show();
        
        parentStage.close();
    }
}
