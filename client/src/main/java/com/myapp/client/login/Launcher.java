package com.myapp.client.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Launcher extends Application {

    private static Stage primaryStageObj;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStageObj=stage;
        Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("gui/LoginInterface.fxml"));
        Scene scene = new Scene(root) ;

        primaryStageObj.setTitle("ChatRoom");
        primaryStageObj.initStyle(StageStyle.DECORATED);
        primaryStageObj.setScene(scene);
        primaryStageObj.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }

}