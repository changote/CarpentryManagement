package Controller;

import Alert.Alerts;
import Carpentry.Carpentry;
import Carpentry.Materials.Article;
import Carpentry.Materials.Material;
import Main.Main;
import com.sun.javafx.logging.Logger;
import com.sun.javafx.logging.PlatformLogger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import json.CarpentryJson;
import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ArticleController implements Initializable {
    @FXML private TableView<Article> tableArticleView;
    @FXML private TableColumn<Article, String> articleNameColumn;
    @FXML private TableColumn<Article, Integer> articleCostColumn;
    @FXML private TableColumn<Article, Integer> articleIdColumn;
    @FXML private TableColumn<Article, Double> articleFinalPriceColumn;

    @FXML private TextField keywordTextField;


    CarpentryJson json = new CarpentryJson();
    MaterialController matC= new MaterialController();
    /**
     * Read the json file
     */

    Carpentry carp = json.readCarpentryJson();

    SceneController scene = new SceneController();
    Alerts alerts = new Alerts();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (carp == null) {
            matC.getCarp();
        }
        tableArticleView.setItems(carp.getListArticle());

        UpdateTable();
        //Update the table to allow for the name and cost fields

        //to be editable
        tableArticleView.setEditable(true);
    }

    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void UpdateTable(){

        articleNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        articleNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        articleCostColumn.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        articleCostColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        articleIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleFinalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        articleFinalPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        searchBarProduct(carp.getListArticle());

    }

    public void onEditNameMaterialChanged(TableColumn.CellEditEvent<Article,String>articleStringCellEditEvent){
        carp = json.readCarpentryJson();
        Article art = tableArticleView.getSelectionModel().getSelectedItem();
        art.setName(articleStringCellEditEvent.getNewValue());
        UpdateTable();

        json.saveCarpentryJson(carp);
    }

    public void onEditCostMaterialChanged(TableColumn.CellEditEvent<Article,Integer>articleIntegerCellEditEvent){

        Article art = tableArticleView.getSelectionModel().getSelectedItem();
        art.setCostPrice(articleIntegerCellEditEvent.getNewValue());
        art.setFinalPrice(art.getCostPrice()*(carp.getPercentageWorkForce()+1));
        json.saveCarpentryJson(carp);
        UpdateTable();


    }
    public void searchBarProduct(ObservableList<Article> dataMaterial ){
        FilteredList<Article> filteredData = new FilteredList<>(dataMaterial, b -> true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(articleSearch -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(articleSearch.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                } else if(articleSearch.getId().toString().indexOf(searchKeyword) > -1){
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<Article> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableArticleView.comparatorProperty());

        tableArticleView.setItems(sortedData);

    }


    @FXML
    private void clickDelete() {
        boolean bool = alerts.showAlertDelete();
        if(bool){
            Article selected = tableArticleView.getSelectionModel().getSelectedItem();
            carp.deleteArticle(selected);
            json.saveCarpentryJson(carp);
            System.out.print("borrado");
            tableArticleView.refresh();
        }
    }


    public void ClickBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = loader.load();
        SceneController sceneController = loader.getController();
        sceneController.setStage(stage);

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ClickAddArticle() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newArticle.fxml"));
        Parent root = loader.load();


        NewArticleController articleController = loader.getController();
        articleController.setStage(stage);

        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML public void ClickWorkForce() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/newPercentage.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 264, 174);
        Stage stg = new Stage();
        stg.setResizable(false);
        NewPercentageController newPercentageController = fxmlLoader.getController();
        newPercentageController.setStage(stg);

        stg.setTitle("Porcentaje ganancia");
        Image icon = new Image("/icons/jticon.png");
        stg.getIcons().add(icon);

        stg.setScene(scene);
        stg.show();
    }


}





