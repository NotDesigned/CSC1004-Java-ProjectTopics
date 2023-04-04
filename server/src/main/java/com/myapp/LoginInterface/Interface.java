package com.myapp.LoginInterface;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Interface{
    static public void login(Stage stage){        

        stage.setTitle("Login");

        HBox hBox=new HBox();

        Button button= new Button("login");

        hBox.getChildren().add(button);

        Scene scene = new Scene(hBox,800,400);

        stage.setScene(scene);

        stage.setWidth(1200);
        stage.setHeight(700);
        stage.show();
    }
    
    public Interface() {

    }
}
