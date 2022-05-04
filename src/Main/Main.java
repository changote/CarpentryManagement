package Main;

import Carpentry.Materials.Material;
import Controller.SceneController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Carpentry.Carpentry;
import json.CarpentryJson;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;




    @Override
    public void start(Stage primaryStage) throws Exception{

        stg = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = loader.load();
        SceneController sceneController = loader.getController();
        sceneController.setStage(stg);
        primaryStage.setTitle("Carpentry Management");
        Image icon = new Image("/icons/jticon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }
    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

}

