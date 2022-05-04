package Controller;

import Alert.Alerts;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class SceneController {


    private Stage stg;
    Alerts alerts = new Alerts();


    @FXML public void ClickMaterial() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Materials.fxml"));
        Parent root = loader.load();
        MaterialController materialController = loader.getController();
        materialController.setStage(stg);
        stg.setScene(new Scene(root));
        stg.show();
    }

    @FXML public void ClickArticle() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Articles.fxml"));
        Parent root = loader.load();
        ArticleController articleController = loader.getController();
        articleController.setStage(stg);
        stg.setScene(new Scene(root));
        stg.show();
    }
    public void ClickAddArticle() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newArticle.fxml"));
        Parent root = loader.load();

        NewArticleController articleController = loader.getController();
        articleController.setStage(stg);

        stg.setScene(new Scene(root));
        stg.show();
    }

    public void setStage(Stage stg) {
        this.stg=stg;
    }


//
//
//    public void changeScene(String fxml) throws IOException{
//        try {
//            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setScene(new Scene(pane));
//            stage.show();
//
//        }
//        catch (IOException e) {
//            alerts.showAlertCategoryError();
//        }
//    }
}
