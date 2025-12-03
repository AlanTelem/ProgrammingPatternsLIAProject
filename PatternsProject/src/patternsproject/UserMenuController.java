/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
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
    private TableView<Map<String, Object>> quickViewTableView;
    @FXML
    private TableColumn<Map, String> friendCol;
    @FXML
    private TableColumn<Map, String> collectionCol;

    private FriendshipDAOImpl friendDAO = new FriendshipDAOImpl();
    
    private ObservableList<Map<String, Object>> collectionList = FXCollections.observableArrayList();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        friendCol.setCellValueFactory(new MapValueFactory<>("userKey"));
        collectionCol.setCellValueFactory(new MapValueFactory<>("collectionKey"));
        
        loadTableData();
    }
    
    private void loadTableData(){
        collectionList.clear(); //Ensures no duplicates at any point
        
        List<Map<String, Object>> data = friendDAO.getFriendsCollections(UserSession.getInstance().getUserId());
        
        collectionList.addAll(data);
        
        quickViewTableView.setItems(collectionList);
    }
    
    public void switchWelcomeText(String text){
        String defaultText = welcomeLbl.getText();
        
        String newText = defaultText + " " + text;
        
        welcomeLbl.setText(newText);
    }
    
    @FXML
    public void switchToCollectionsMenu(ActionEvent event){
       Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("collectionsMenu.fxml"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collections Application");
        stage.show();
        
        parentStage.close();
    }
    
    public void switchToFriendsMenu(ActionEvent event){
       Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendsMenu.fxml"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collections Application");        
        stage.show();
        
        parentStage.close();
    }
    
    @FXML
    public void logOut(ActionEvent event){
        UserSession.logOutInstance();
        
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
        stage.setTitle("Collections Application");
        stage.show();
        
        parentStage.close();
    }
}
