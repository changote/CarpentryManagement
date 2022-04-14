package Main;

import Carpentry.Materials.Material;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        primaryStage.setTitle("Carpentry Management");
        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.show();




    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {

        launch(args);




    }

    public static Carpentry Harcodeo(){
        Carpentry carp = new Carpentry("Pitu Tejeria","sadasd");
        Material mat = new Material("auto",123);
        carp.addMaterial(mat);
        Material mat1 = new Material("casa",123);
        carp.addMaterial(mat1);
        Material mat2 = new Material("pepe",123);
        carp.addMaterial(mat2);
        Material mat3 = new Material("pepe",123);
        carp.addMaterial(mat3);

        return carp;
    }
}

