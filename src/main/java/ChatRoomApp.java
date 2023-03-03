// Import required libraries
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ChatRoomApp extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Create the login form
        Label titleLabel = new Label("Chat Room Login");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        loginButton = new Button("Login");
        loginButton.setOnAction(event -> handleLogin());

        VBox loginForm = new VBox();
        loginForm.setSpacing(10);
        loginForm.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        loginForm.setPadding(new Insets(20));

        // Create the main chat room
        Label chatTitleLabel = new Label("Chat Room");
        chatTitleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox chatRoom = new VBox();
        chatRoom.setSpacing(10);
        chatRoom.getChildren().addAll(chatTitleLabel);

        // Create the main window
        BorderPane root = new BorderPane();
        root.setLeft(loginForm);
        root.setCenter(chatRoom);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat Room App");
        primaryStage.show();
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // TODO: Implement login functionality using username and password

        // If login is successful, switch to chat room
        usernameField.clear();
        passwordField.clear();
        loginButton.setDisable(true);
    }
}
