/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author lukec
 */
public class FriendsMenuController implements Initializable {

    @FXML
    private Button addFriendsBtn;

    @FXML
    private Button exitFriendBtn;

    @FXML
    private Label friendLbl;

    @FXML
    private ListView<String> friendListView;
    
    private ObservableList<String> friendsList = FXCollections.observableArrayList();
    
    private FriendshipDAOImpl friendsDAO = new FriendshipDAOImpl();
    
    private UserDAOImpl userDAO = new UserDAOImpl();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int currentUserId = UserSession.getInstance().getUserId();
        
        List<String> databaseFriends = friendsDAO.getFriendsName(currentUserId);
        
        friendsList.clear();
        
        friendsList.addAll(databaseFriends);
        
        friendListView.setItems(friendsList);
    }
    
    public void exit(ActionEvent event){
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.FXML"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        UserMenuController controller = loader.getController();
        
        controller.switchWelcomeText(userDAO.getUsernameById((UserSession.getInstance().getUserId())));
        
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collection Application");
        stage.show();
        
        parentStage.close();
    }
    
    public void addFriend(ActionEvent event){
        //Make new scene
    }
}
