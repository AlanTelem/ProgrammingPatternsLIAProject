/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import patternsproject.Implementations.UserDAOImpl;
import patternsproject.Implementations.FriendshipDAOImpl;
import patternsproject.Models.Friendship;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.SpringLayout;
import org.sqlite.SQLiteException;
import patternsproject.UserSession;

public class AddFriendsController implements Initializable {

    @FXML
    private Button addFriendBtn;

    @FXML
    private Label addFriendLbl;

    @FXML
    private TextField addFriendTextField;
    
    private FriendshipDAOImpl friendDAO = new FriendshipDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void addFriendClick(ActionEvent e){
        String friendName = addFriendTextField.getText();
        
        if (userDAO.userExists(friendName)){
            Friendship friendship = new Friendship(UserSession.getInstance().getUserId(), userDAO.getIdByUser(friendName));
            
            friendDAO.createFriendship(friendship);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding friendship");
            alert.setHeaderText("Friendship not added because user does not exist");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }
}
