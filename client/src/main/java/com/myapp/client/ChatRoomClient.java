import com.myapp.client.login.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ChatRoomClient extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        com.myapp.client.login.Interface.login(stage);
//        com.myapp.messages.Message.MainMethod();

    }

    public static void main(String[] args) {
        launch();
    }

}