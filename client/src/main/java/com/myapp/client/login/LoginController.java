package com.myapp.client.login;

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

public class LoginController implements Initializable {
    @FXML
    private Button loginbtn;
    @FXML
    private PasswordField pwdinput;
    @FXML
    private Button regbtn;
    @FXML
    private TextField unameinput;
    @FXML
    private Label errorLabel;

    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    @FXML
    public void handleLoginButtonAction(ActionEvent event) {
        String username = unameinput.getText();
        String password = pwdinput.getText();

        if(username.equals("admin") && password.equals("1234")) {
            errorLabel.setText("");
            System.out.println("login successful");
            FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("gui/ChatView.fxml"));

            //TODO: 进入主界面
        } else {
            errorLabel.setText("username/password wrong");
            System.out.println("login failed");
        }
    }
    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){

    }
}