package Controller;

import Alert.Alerts;
import Carpentry.Carpentry;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import json.CarpentryJson;

import java.awt.event.ActionEvent;

public class NewPercentageController {

    Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }

    CarpentryJson carpentryJson = new CarpentryJson();

    Carpentry carp = carpentryJson.readCarpentryJson();

    ArticleController ar = new ArticleController();

    Alerts alerts = new Alerts();


    @FXML private TextField percentageTextField;

    @FXML public void ClickOkNewPercentage(){
        System.out.print(carp.getPercentageWorkForce());
        try{
            carp.setPercentageWorkForce(Double.parseDouble(percentageTextField.getText())/100);
            carp.refreshFinalPrice();
            carp.mostrarFinal();
            carpentryJson.saveCarpentryJson(carp);
            stage.close();
        }
        catch (Exception e){
            alerts.showAlertCostError();
        }


    }
}
