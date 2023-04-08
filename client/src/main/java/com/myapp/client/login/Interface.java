package com.myapp.client.login;
import java.io.FileInputStream;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Interface{
    static Stage stage;
    static public void CreateInterface(Stage stage){

    }
    static public void login(Stage _stage)throws Exception{
        stage=_stage;
        CreateInterface(stage);

    }    
}
