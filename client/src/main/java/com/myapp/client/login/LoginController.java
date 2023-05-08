package com.myapp.client.login;

import com.myapp.client.chat.ChatController;
import com.myapp.client.chat.Listener;
import com.myapp.client.gui.ControllStage;
import com.myapp.client.gui.Launcher;
import com.myapp.client.gui.StageController;
import com.myapp.messages.Message;
import com.myapp.messages.MsgType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.myapp.client.gui.Launcher.mainViewRes;
import static com.myapp.client.gui.Launcher.stageController;

public class LoginController implements Initializable, ControllStage {

    private static final String mainViewID= "ChatInterface";

    private static final String loginViewID= "LoginInterface";
    public static volatile boolean loginlock,loginvalue;
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
        ChatCon=(ChatController) stageController.loadStage(mainViewID,mainViewRes);
        ChatCon.setusername(username);
        Listener listener = new Listener(hostname, port, username,password, ChatCon);
        Thread x = new Thread(listener);
        loginvalue=false;loginlock=true;
        x.start();
        while(loginlock);
        System.out.println("OutLock");
        if (loginvalue) {
            Launcher.getController().setStage(mainViewID, loginViewID);

            errorLabel.setText("");
            System.out.println("login successful");
        } else {
            errorLabel.setText("Username/password wrong");
            System.out.println("login failed");
        }
    }
    @FXML
    public void handleRegisterButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String username = unameinput.getText();
        String password = pwdinput.getText();
        int port = Integer.parseInt(portinput.getText());
        String hostname = hostinput.getText();

        Socket socket = new Socket(hostname, port);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        InputStream is = socket.getInputStream();
        ObjectInputStream input = new ObjectInputStream(is);

        Message regis = new Message();

        regis.setType(MsgType.REGISTER);

        regis.setName(username);
        regis.setMsg(password);
        oos.writeObject(regis);
        oos.flush();
        Message ret = (Message) input.readObject();
        if (ret.getMsg().equals("Success")) {
            System.out.println("Register successful");
            errorLabel.setText("Register successful");
        } else {
            errorLabel.setText("Username Duplicated");
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