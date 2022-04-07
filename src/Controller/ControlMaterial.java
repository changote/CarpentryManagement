package Controller;

import Carpentry.Materials.Article;
import Carpentry.Materials.Material;
import Main.Main;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Carpentry.Carpentry;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ControlMaterial implements Initializable {
    Carpentry carp = Main.Harcodeo();
    @FXML
    private Button toMaterial;
    @FXML
    private Button toArticle;
    @FXML
    private Button toBudget;
    @FXML
    private Button goBack;
    @FXML
    private TableView<Material> tableMaterialView = new TableView<Material>();
    @FXML
    private TableView<Article> tableArticleView = new TableView<Article>();
    @FXML
    private TableColumn<Material,String> tableMaterialName = new TableColumn<>("name");
    @FXML
    private TableColumn<Article,String> tableArticleName = new TableColumn<>("name");
    @FXML
    private TableColumn<Material, Integer> tableMaterialCost = new TableColumn<>("cost");
    @FXML
    private TableColumn<Article, Integer> tableArticleCost = new TableColumn<>("cost");
    @FXML
    private TableColumn<Article, Integer> tableArticleFinalPrice = new TableColumn<>("finalCost");






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        loadTable();


    }

    public void loadTable(){
        //Harcodeo
        Carpentry carp = Main.Harcodeo();
        //Inserto los datos de mi ArrayList en una ObservableList de la columna
        ObservableList<Material> dataMaterial = FXCollections.observableArrayList(carp.getListMaterial());
        tableMaterialName.setCellValueFactory(new PropertyValueFactory<Material,String>("name"));
        tableMaterialCost.setCellValueFactory(new PropertyValueFactory<Material,Integer>("costPrice"));
        tableArticleName.setCellValueFactory(new PropertyValueFactory<Article,String>("name"));
        tableArticleCost.setCellValueFactory(new PropertyValueFactory<Article,Integer>("costPrice"));
        tableArticleFinalPrice.setCellValueFactory(new PropertyValueFactory<Article,Integer>("finalPrice"));



        tableMaterialView.getColumns().clear();
        tableMaterialView.getColumns().addAll(tableMaterialName,tableMaterialCost);
        tableMaterialView.setItems(dataMaterial);
    }


    public void ClickMaterial(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/fxml/Materials.fxml");
    }
    public void ClickArticle(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/fxml/Articles.fxml");
    }
    public void ClickBudget(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/fxml/Budget.fxml");
    }
    public void ClickBack(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/fxml/Home.fxml");
    }


}
