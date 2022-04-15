package Controller;

import Carpentry.Carpentry;
import Carpentry.Materials.Article;
import Carpentry.Materials.Material;
import Main.Main;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import json.CarpentryJson;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
    private Button addNewMaterial;
    @FXML
    private Label nameErrorLabel = new Label();
    @FXML
    private Label costErrorLabel = new Label();
    @FXML
    private TextField keywordTextField = new TextField();
    @FXML
    private TextField materialNameTextField = null;
    @FXML
    private TextField materialCostTextField = null;
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

    Carpentry carp = Main.Harcodeo();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //CarpentryJson json = new CarpentryJson();
        //Leo el archivo json
        //Carpentry carp = json.readCarpentryJson();

        ObservableList<Material> dataMaterial = loadTable();
        searchBarProduct(dataMaterial);
        //json.saveCarpentryJson(carp);
    }

    public ObservableList<Material> loadTable(){

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


        return dataMaterial;

    }
    public void searchBarProduct(ObservableList<Material> dataMaterial ){
        FilteredList<Material> filteredData = new FilteredList<>(dataMaterial, b -> true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(tableMaterialName -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(tableMaterialName.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<Material>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableMaterialView.comparatorProperty());

        tableMaterialView.setItems(sortedData);

    }


    public void ClickNewMaterial(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("/fxml/AddMaterial.fxml");
    }

    public void ClickAddMaterial(ActionEvent actionEvent) throws IOException{

        boolean flagName = false;
        boolean flagCost = false;
        Material newMat = null;
        try {
            newMat = new Material(materialNameTextField.getText(),Integer.parseInt(materialCostTextField.getText()));
            if(isInt(materialCostTextField) && (materialNameTextField.getText().length() < 3)){
                carp.addMaterial(newMat);
                Main m = new Main();
                m.changeScene("/fxml/Materials.fxml");
            }
        }
        catch (Exception e){
            if (materialNameTextField.getText().length() < 3) {
                nameErrorLabel.setText("Ingrese al menos tres caracteres.");
            }
            else{
                flagName = true;
            }
            if (!isInt(materialCostTextField)) {
                costErrorLabel.setText("Ingrese solo numeros.");
            }
            else{
                flagCost = true;
            }

        }
        if(flagCost && flagName)
        {
            carp.addMaterial(newMat);
            loadTable();
            Main m = new Main();
            m.changeScene("/fxml/Materials.fxml");
        }


    }

    public void newMaterialButtonPushed() throws IOException {
        Material newMat = new Material(materialNameTextField.getText(),Integer.parseInt(materialCostTextField.getText()));
        carp.addMat(newMat);
        carp.mostrarMaterial();
        tableMaterialView.getColumns().addAll(tableMaterialName,tableMaterialCost);
        tableMaterialView.getItems();
        Main m = new Main();
        m.changeScene("/fxml/Materials.fxml");
    }

    private boolean isInt(TextField input){
        try{
            int cost = Integer.parseInt(input.getText());
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
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
