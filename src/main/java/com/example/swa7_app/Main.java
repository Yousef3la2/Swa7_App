package com.example.swa7_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        //Parent root = loader.load();

        //Scene scene = new Scene(root, 550, 410);

        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Swa7 (The tourist guide)");
        stage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("file:icon.png");
        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}