package com.myapp.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Launcher extends Application {

    private static Stage primaryStageObj;
    public static StageController stageController;
    public static StageController getController(){return stageController;}

    public static final String mainViewID= "ChatInterface";
    public static final String mainViewRes= "ChatInterface.fxml";

    public static final String loginViewID= "LoginInterface";
    public static final String loginViewRes= "LoginInterface.fxml";

    @Override
    public void start(Stage stage) throws IOException {
        primaryStageObj=stage;
        stageController=new StageController();
        stageController.setPrimaryStage("pStage",primaryStageObj);
        stageController.loadStage(loginViewID,loginViewRes,StageStyle.UNDECORATED);
        stageController.setStage(loginViewID);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }

}