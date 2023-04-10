package com.app.project;

import com.app.project.datasource.DataStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public void start(Stage primaryStage) throws Exception {
        DataStore.initializeDataBase();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayProperties.fxml"));
//        Parent root = fxmlLoader.load();
//        primaryStage.setScene(new Scene(root, 1000, 800));
//        primaryStage.setTitle("Test");
//        primaryStage.show();

//        MainView view = new MainView();
//        Scene scene = new Scene(view, 500, 500);
//        primaryStage.setTitle("Sample JavaFx MVC APP");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        IPropertyController controller = ControllerFactory.getController("");
//        controller.displayAll();
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/WelcomePage.fxml"));
        Scene scene = new Scene(root);
        this.scene = scene;
        stage.setScene(scene);
        stage.show();

    }
}
