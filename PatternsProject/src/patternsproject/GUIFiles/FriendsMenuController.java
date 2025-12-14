/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject.GUIFiles;

import patternsproject.Implementations.UserDAOImpl;
import patternsproject.Implementations.FriendshipDAOImpl;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import patternsproject.UserSession;
import patternsproject.I18nManager;
import patternsproject.FxAutoSize;
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
        
        var i18n = I18nManager.get();
    
        int currentUserId = UserSession.getInstance().getUserId();
        
        friendLbl.textProperty().bind(i18n.bind("friendsMenu.title"));
        addFriendsBtn.textProperty().bind(i18n.bind("friendsMenu.addFriends"));
        exitFriendBtn.textProperty().bind(i18n.bind("common.exit"));
        
        List<String> databaseFriends = friendsDAO.getFriendsName(currentUserId);
        
        friendsList.clear();
        
        friendsList.addAll(databaseFriends);
        
        friendListView.setItems(friendsList);
        
        friendListView.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item);
                }
            };

            MenuItem removeItem = new MenuItem("Remove friend");
            removeItem.setOnAction(e -> {
                String friend = cell.getItem();
                if (friend == null) return;

                try {
                    friendsDAO.deleteFriendship(currentUserId, userDAO.getIdByUser(friend));
                    friendsList.remove(friend);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Failed to remove friend:\n" + ex.getMessage())
                            .showAndWait();
                }
            });

            ContextMenu menu = new ContextMenu(removeItem);

            cell.emptyProperty().addListener((obs, wasEmpty, isEmpty) ->
                    cell.setContextMenu(isEmpty ? null : menu));

            return cell;
        });
        
        FxAutoSize.install(friendLbl);
    }
    
    public void exit(ActionEvent event){
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.FXML"));
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
    
    public void addFriend(ActionEvent event){
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFriends.FXML"));
        try{ root = loader.load();}
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collection Application");
        stage.show();
        
        parentStage.close();    }
}
