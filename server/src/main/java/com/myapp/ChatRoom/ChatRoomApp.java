import com.myapp.LoginInterface.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChatRoomApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        com.myapp.LoginInterface.Interface.login(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}