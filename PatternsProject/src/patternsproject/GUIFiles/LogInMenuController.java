/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package patternsproject.GUIFiles;

import patternsproject.Implementations.UserDAOImpl;
import patternsproject.Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import patternsproject.UserSession;


/**
 * FXML Controller class
 *
 * @author 6119391
 */
public class LogInMenuController implements Initializable {

    @FXML
    private VBox logInForm;
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
    @FXML
    private MenuButton languageMenuBtn;;
    @FXML 
    private MenuItem englishItem;
    @FXML 
    private MenuItem frenchItem;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private UserDAOImpl userDAO = new UserDAOImpl();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var i18n = I18nManager.get();
        
        usernameLbl.textProperty().bind(i18n.bind("login.username.label"));
        passwordLbl.textProperty().bind(i18n.bind("login.password.label"));
        usernameTxtField.promptTextProperty().bind(i18n.bind("login.username.prompt"));
        passwordField.promptTextProperty().bind(i18n.bind("login.password.prompt"));

        logInBtn.textProperty().bind(i18n.bind("login.button"));
        guestLogInBtn.textProperty().bind(i18n.bind("login.guest"));
        createUserBtn.textProperty().bind(i18n.bind("login.createUser"));

        languageMenuBtn.textProperty().bind(i18n.bind("menu.language"));
        englishItem.textProperty().bind(i18n.bind("lang.english"));
        frenchItem.textProperty().bind(i18n.bind("lang.french"));
        
        FxAutoSize.install(usernameLbl);
    }    
    
    @FXML 
    private void setEnglish() { I18nManager.get().setLocale(Locale.ENGLISH); }
    
    @FXML 
    private void setFrench()  { I18nManager.get().setLocale(Locale.FRENCH); }

    
    public void authenticate(ActionEvent event){
        String password = passwordField.getText() + "encrypted";
        if (usernameTxtField.getText().equalsIgnoreCase("Guest")){
            Alert guestAlert = new Alert(Alert.AlertType.ERROR);
            guestAlert.setTitle("Guest Conflict");
            guestAlert.setHeaderText("Invalid Username");
            guestAlert.setContentText("You cannot log in with the guest username. To log in as a guest, press the \"Guest Log In\" button.");
            guestAlert.showAndWait();
        } else if (usernameTxtField.getText().equals(userDAO.getUserByUsername(usernameTxtField.getText())) && password.equals(userDAO.getPasswordByUsername(usernameTxtField.getText()))){
            
            UserSession.setInstance(userDAO.getIdByUser(usernameTxtField.getText()));
            
            Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/patternsproject/UserMenu.fxml"));
            try{ root = loader.load();}
            catch (IOException ioe){
                ioe.printStackTrace();
            }

            UserMenuController controller = loader.getController();

            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setScene(new Scene(root));
            stage.setTitle("Collections Application");
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
        if (usernameTxtField.getText().contains("Guest")){
            Alert guestAlert = new Alert(Alert.AlertType.ERROR);
            guestAlert.setTitle("Creation Conflict");
            guestAlert.setHeaderText("Invalid Username");
            guestAlert.setContentText("You cannot create an account with the guest username. Please create an account with a unique username");
            guestAlert.showAndWait();
        } else if (userDAO.userExists(usernameTxtField.getText())){
            Alert creationAlert = new Alert(Alert.AlertType.ERROR);
            creationAlert.setTitle("Creation Error");
            creationAlert.setHeaderText("User Already Exists");
            creationAlert.setContentText("The username you used already exists");
            creationAlert.showAndWait();
        } else {
            User newUser = new User(usernameTxtField.getText(), (passwordField.getText()));
            userDAO.registerUser(newUser);
            Alert created = new Alert(Alert.AlertType.CONFIRMATION);
            created.setTitle("User Created");
            created.setHeaderText("The User Has Been Created");
            created.setContentText("You may now log in with your new user");
            created.showAndWait();
        }
    }
    
    public void switchToGuestUserMenu(ActionEvent event){
        UserSession.setInstance(userDAO.getIdByUser("Guest"));
        
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
        try{ root = loader.load();}
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        
        UserMenuController controller = loader.getController();
                
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setScene(new Scene(root));
        stage.setTitle("Collections Application: Guest");
        stage.show();
        
        parentStage.close();
    }
}
