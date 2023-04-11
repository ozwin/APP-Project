package com.app.project;

import com.app.project.datasource.DataStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    public static Stage stage;
    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    public static void navigate() {
//        let's make this utility function or something
        try {
            App.stage.setScene(scene);
            App.stage.show();
        } catch (Exception ex) {

        }
    }

    public static void errorpage() throws IOException {
//        call this in case of exception
        Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("/ErrorPage.fxml")));
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.setScene(new Scene(root));
        stage.show();
        App.stage.close();
    }

    public void start(Stage primaryStage) throws Exception {
        DataStore.initializeDataBase();
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomePage.fxml")));
        Scene scene = new Scene(root);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("OSM Real Estate");
        stage.show();

    }
}
