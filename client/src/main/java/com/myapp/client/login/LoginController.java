package com.myapp.client.login;

import com.myapp.client.chat.ChatController;
import com.myapp.client.chat.Listener;
import com.myapp.client.gui.ControllStage;
import com.myapp.client.gui.Launcher;
import com.myapp.client.gui.StageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static com.myapp.client.gui.Launcher.mainViewRes;
import static com.myapp.client.gui.Launcher.stageController;

public class LoginController implements Initializable, ControllStage {

    private static final String mainViewID= "ChatInterface";

    private static final String loginViewID= "LoginInterface";
    @FXML
    private Button loginbtn;
    @FXML
    private PasswordField pwdinput;
    @FXML
    private Button regbtn;
    @FXML
    private TextField unameinput;
    @FXML
    private TextField portinput;
    @FXML
    private TextField hostinput;
    @FXML
    private Label errorLabel;
    public static ChatController ChatCon;

    private static StageController myStageController;

    public LoginController() {}

    @FXML
    public void handleLoginButtonAction(ActionEvent event) {
        String username = unameinput.getText();
        String password = pwdinput.getText();
        int port = Integer.parseInt(portinput.getText());
        String hostname = hostinput.getText();
        port=2521;
        hostname="127.0.0.1";
        if(true||(username.equals("Adscn") && password.equals("theadscn"))||(username.equals("Bob") && password.equals("thebob")))
        {
            errorLabel.setText("");
            System.out.println("login successful");

            //TODO: 进入主界面

            ChatCon=(ChatController) stageController.loadStage(mainViewID,mainViewRes);
            ChatCon.setusername(username);
            Launcher.getController().setStage(mainViewID,loginViewID);
            Listener listener = new Listener(hostname, port, username, ChatCon);
            Thread x = new Thread(listener);
            x.start();

        } else {
            errorLabel.setText("Username/password wrong");
            System.out.println("login failed");
        }
    }
    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){
    }
    @Override
    public void setStageController(StageController stageController){
        this.myStageController=stageController;
    }
}