/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import patternsproject.Implementations.UserDAOImpl;
import patternsproject.Implementations.FriendshipDAOImpl;
import patternsproject.Models.Friendship;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    
    @FXML
    private Button returnBtn;
    
    private FriendshipDAOImpl friendDAO = new FriendshipDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var i18n = I18nManager.get();

        addFriendLbl.textProperty().bind(i18n.bind("addFriends.instruction"));
        addFriendBtn.textProperty().bind(i18n.bind("addFriends.add"));
        returnBtn.textProperty().bind(i18n.bind("common.return"));
        
        FxAutoSize.install(addFriendLbl);
    }    
    
    public void addFriendClick(ActionEvent e){
        String friendName = addFriendTextField.getText();
        
        if (userDAO.userExists(friendName)){
            Friendship friendship = new Friendship(UserSession.getInstance().getUserId(), userDAO.getIdByUser(friendName));
            
            friendDAO.createFriendship(friendship);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Friendship successfully added");
            alert.setHeaderText("Friendship with User: " + friendName + " is successfully added" );
            alert.setContentText("Yippee!!!!!!!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding friendship");
            alert.setHeaderText("Friendship not added because user does not exist");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }
    
    public void returnAction(ActionEvent event) {
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendsMenu.FXML"));
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
}
