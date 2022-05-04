package Controller;

import Alert.Alerts;
import Carpentry.Carpentry;
import Carpentry.Materials.Article;
import Carpentry.Materials.Material;
import Carpentry.Materials.MaterialClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import json.CarpentryJson;
import org.controlsfx.control.textfield.TextFields;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewArticleController implements Initializable {
    @FXML private TextField nameTextField;
    @FXML private TextField costTextField;

    @FXML private ComboBox<MaterialClass> comboboxMaterialClass;


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
        comboboxMaterialClass.getItems().addAll(MaterialClass.values());
    }

    @FXML private void clickAddMaterialToArticleList() {
        try {
            Article newArt = new Article(nameTextField.getText(), (Integer.parseInt(costTextField.getText())),idArticle(),comboboxMaterialClass.getValue().toString());
            newArt.setFinalPrice(Integer.parseInt(costTextField.getText())*(carp.getPercentageWorkForce()+1));
            carp.newArticle(newArt);
            json.saveCarpentryJson(carp);
            ClickAddAux();

        } catch (Exception e) {
            if (nameTextField.getText().equals("") || nameTextField.getText().isBlank()) { ////////OBTIENE UN NOMBRE
                alerts.showAlertNameError();
            }else if (costTextField.getText().equals("") || costTextField.getText().chars().allMatch(Character::isWhitespace) || !costTextField.getText().contains("[a-zA-Z]+")) {
                alerts.showAlertCostError(); ///////////OBTIENE UN NUMERO
            }

        }
    }

    public Integer idArticle(){
        int newId = 0;
        if(carp != null){
            for (Article art : carp.getListArticle()){
                newId = art.getId()+1;
            }
        }
        else
            return 0;
        return newId;
    }


    @FXML private void ClickCancelNA() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Articles.fxml"));
        Parent root = loader.load();
        ArticleController articleController = loader.getController();
        articleController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void ClickAddAux() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Articles.fxml"));
        Parent root = loader.load();
        ArticleController articleController = loader.getController();
        articleController.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboboxMaterial();

    }
}
