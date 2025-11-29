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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 6119391
 */
public class LogInMenuController implements Initializable {

    @FXML
    private AnchorPane logInForm;
    @FXML
    private Label usernameLbl;
    @FXML
    private Label passwordLbl;
    @FXML
    private TextField usernameTxtField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button guestLogInBtn;
    @FXML
    private Button createUserBtn;
    @FXML
    private Button logInBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private UserDAOImpl userDAO = new UserDAOImpl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void authenticate(ActionEvent event){
        String password = passwordField.getText() + "encrypted";
        if (usernameTxtField.getText().equals(userDAO.getUserByUsername(usernameTxtField.getText())) && password.equals(userDAO.getPasswordByUsername(usernameTxtField.getText()))){
            Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.FXML"));
            try{ root = loader.load();}
            catch (IOException ioe){
                System.err.println(ioe.getMessage());
            }

            UserMenuController controller = loader.getController();

            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setScene(new Scene(root));
            stage.setTitle("Collections Application");
            controller.switchWelcomeText(usernameTxtField.getText());
            stage.show();

            parentStage.close();
        } else {
            Alert userAlert = new Alert(Alert.AlertType.ERROR);
            userAlert.setTitle("Log In Error");
            userAlert.setHeaderText("Invalid Username Or Password");
            userAlert.setContentText("Your credentials are invalid or do not exist");
            userAlert.showAndWait();
        }
    }
    
    public void createUser(ActionEvent event){
        if (userDAO.userExists(usernameTxtField.getText())){
            Alert creationAlert = new Alert(Alert.AlertType.ERROR);
            creationAlert.setTitle("Creation Error");
            creationAlert.setHeaderText("User Already Exists");
            creationAlert.setContentText("The username you used already exists");
            creationAlert.showAndWait();
        } else {
            User newUser = new User(usernameTxtField.getText(), (passwordField.getText() + "encrypted"));
            userDAO.registerUser(newUser);
            Alert created = new Alert(Alert.AlertType.CONFIRMATION);
            created.setTitle("User Created");
            created.setHeaderText("The User Has Been Created");
            created.setContentText("You may now log in with your new user");
            created.showAndWait();
        }
    }
    
    public void switchToGuestUserMenu(ActionEvent event){
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.FXML"));
        try{ root = loader.load();}
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        UserMenuController controller = loader.getController();
                
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collections Application");
        controller.switchWelcomeText("Guest");
        stage.show();
        
        parentStage.close();
    }
}
