package com.app.project;

import com.app.project.datasource.DataStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
//            setAPPIcon(stage,"");
            Image icon = new Image("/icons/logo.png");
            stage.getIcons().add(icon);
        } catch (Exception ex) {

        }
    }

    public static void errorPage() throws IOException {
//        call this in case of exception
        Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("/ErrorPage.fxml")));
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.setScene(new Scene(root));
        stage.show();
//        setAPPIcon(stage,"");
        Image icon = new Image("/icons/logo.png");
        stage.getIcons().add(icon);
        App.stage.close();
    }

    public void start(Stage primaryStage) throws Exception {
        try {
            DataStore.initializeDataBase();
            this.stage = primaryStage;

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/WelcomePage.fxml")));
            Scene scene = new Scene(root);
            this.scene = scene;
            stage.setScene(scene);
//            setAPPIcon(stage,"");
            Image icon = new Image("/icons/logo.png");
            stage.getIcons().add(icon);
            stage.setTitle("OSM Real Estate");

            stage.show();
        }catch (Exception ex){
            App.errorPage();
        }

    }
}
