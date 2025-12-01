/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int currentUserId = UserSession.getInstance().getUserId();
        
        List<String> databaseFriends = friendsDAO.getFriendsName(currentUserId);
        
        friendsList.clear();
        
        friendsList.addAll(databaseFriends);
        
        friendListView.setItems(friendsList);
    }
    
}
