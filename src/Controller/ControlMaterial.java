package Controller;

import Carpentry.Materials.Article;
import Carpentry.Materials.Material;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ResourceBundle;

public class ControlMaterial implements Initializable {
    @FXML
    private Button toMaterial;
    @FXML
    private Button toArticle;
    @FXML
    private Button toBudget;
    @FXML
    private Button goBack;
    @FXML
    private TableView<Material> tableMaterialView;
    @FXML
    private TableView<Article> tableArticleView;
    @FXML
    private TableColumn<Material,String> tableMaterialName;
    @FXML
    private TableColumn<Article,String> tableArticleName;
    @FXML
    private TableColumn<Material, Integer> tableMaterialCost;
    @FXML
    private TableColumn<Article, Integer> tableArticleCost;
    @FXML
    private TableColumn<Article, Integer> tableArticleFinalPrice;

    ObservableList<Material> list = FXCollections.observableArrayList
            (new Material("Clavo",30));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        tableMaterialName.setCellValueFactory(new PropertyValueFactory<Material,String>("tableMaterialName"));
        tableMaterialCost.setCellValueFactory(new PropertyValueFactory<Material,Integer>("tableMaterialCost"));
        tableArticleName.setCellValueFactory(new PropertyValueFactory<Article,String>("tableArticleName"));
        tableArticleCost.setCellValueFactory(new PropertyValueFactory<Article,Integer>("tableArticleCost"));
        tableArticleCost.setCellValueFactory(new PropertyValueFactory<Article,Integer>("tableArticleFinalPrice"));

        tableMaterialView.setItems(list);
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
