package com.myapp.LoginInterface;

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
    static public void login(Stage stage)throws Exception{

        stage.setTitle("Login");

        HBox hBox=new HBox();

        Button button= new Button("login");

        hBox.getChildren().add(button);

        Scene scene = new Scene(hBox,800,400);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setWidth(1200);
//        stage.setHeight(700);
        FileInputStream input=new FileInputStream("a.jpg");
        Image image=new Image(input);
        ImageView imageView=new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);
        HBox hbox=new HBox(imageView);
        Scene scene2 = new Scene(hbox,600,400);
        stage.setScene(scene2);
        stage.setFullScreen(true);
        stage.show();
    }    
}
