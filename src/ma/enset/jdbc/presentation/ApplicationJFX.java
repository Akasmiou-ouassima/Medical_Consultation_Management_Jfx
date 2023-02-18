package ma.enset.jdbc.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ApplicationJFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Scene scene=new Scene(root,608,488);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toString());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Gestion des Cabinet Médical");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
