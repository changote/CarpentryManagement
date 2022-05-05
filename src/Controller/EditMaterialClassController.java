package Controller;

import Alert.Alerts;
import Carpentry.Carpentry;
import Carpentry.Materials.MaterialClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import json.CarpentryJson;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditMaterialClassController implements Initializable {
    @FXML private TextField percentageTextField;
    @FXML private ComboBox comboboxMaterialClass;

    CarpentryJson json = new CarpentryJson();
    /**
     * Read the json file
     */

    Carpentry carp = json.readCarpentryJson();

    Alerts alerts = new Alerts();

    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void loadComboboxMaterial(){
        if(!comboboxMaterialClass.getItems().contains(MaterialClass.Fibros)
                || !comboboxMaterialClass.getItems().contains(MaterialClass.Melaminas)
                || !comboboxMaterialClass.getItems().contains(MaterialClass.Tapacantos)
                || !comboboxMaterialClass.getItems().contains(MaterialClass.Varillas)
                || !comboboxMaterialClass.getItems().contains(MaterialClass.Otros)) {
            comboboxMaterialClass.getItems().addAll(MaterialClass.values());
        }
    }


    @FXML private void ClickCancel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Materials.fxml"));
        Parent root = loader.load();
        MaterialController materialController = loader.getController();
        materialController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML private void clickReady(){
        try{
            carp.editPercentageMaterialClass(comboboxMaterialClass.getValue().toString(),Integer.parseInt(percentageTextField.getText()));
            json.saveCarpentryJson(carp);
            ClickCancel();
        }
        catch (Exception e){
            if(percentageTextField.getText().isBlank() || !percentageTextField.getText().contains("[a-zA-Z]+")){
                alerts.showAlertCostError();
            }
        }
    }

    private void ClickAddAux() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Material.fxml"));
        Parent root = loader.load();
        MaterialController materialController = loader.getController();
        materialController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboboxMaterial();
    }
}
