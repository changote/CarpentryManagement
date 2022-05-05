package Controller;

import Alert.Alerts;
import Carpentry.Carpentry;
import Carpentry.Materials.Material;
import Carpentry.Materials.MaterialClass;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import json.CarpentryJson;

public class MaterialController implements Initializable {
    //configure the table
    @FXML private TableView<Material> tableMaterialView;
    @FXML private TableColumn<Material, String> materialNameColumn;
    @FXML private TableColumn<Material, Double> materialCostColumn;
    @FXML private TableColumn<Material, Integer> materialIdColumn;

    //These instance variables are used to create new Person objects
    @FXML private TextField materialNameTextField;
    @FXML private ComboBox<MaterialClass> materialClassComboBox;
    @FXML private TextField materialCostTextField;

    @FXML private TextField keywordTextField;

    private Stage stg;
    public void setStage(Stage stg) {
        this.stg=stg;
    }

    CarpentryJson json = new CarpentryJson();
    /**
     * Read the json file
     */

    Carpentry carp = json.readCarpentryJson();

    SceneController scene = new SceneController();
    Alerts alerts = new Alerts();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //carp.mostrarMaterial();
        //
        if(carp == null)
            getCarp();
        tableMaterialView.setItems(carp.getListMaterials());
        //Update the table to allow for the name and cost fields
        UpdateTable();
        loadComboboxMaterial();
        //to be editable
        tableMaterialView.setEditable(true);
        //This will allow the table to select multiple rows at once
        //tableMaterialView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void UpdateTable(){
        tableMaterialView.setItems(carp.getListMaterials());

        materialNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        materialNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        materialCostColumn.setCellValueFactory(new PropertyValueFactory<>("CostPrice"));
        materialCostColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        materialIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        materialIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        searchBarProduct(carp.getListMaterials());

    }
    public void onEditNameMaterialChanged(TableColumn.CellEditEvent<Material,String>materialStringCellEditEvent){
        Material mat = tableMaterialView.getSelectionModel().getSelectedItem();
        mat.setName(materialStringCellEditEvent.getNewValue());

        json.saveCarpentryJson(carp);

    }

    public void onEditCostMaterialChanged(TableColumn.CellEditEvent<Material,Double>materialIntegerCellEditEvent){
        Material mat = tableMaterialView.getSelectionModel().getSelectedItem();
        mat.setCostPrice(materialIntegerCellEditEvent.getNewValue());

        json.saveCarpentryJson(carp);

    }

    public void searchBarProduct(ObservableList<Material> dataMaterial ){
        FilteredList<Material> filteredData = new FilteredList<>(dataMaterial, b -> true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(materialSearch -> {

                if(newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(materialSearch.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                } else if(materialSearch.getId().toString().indexOf(searchKeyword) > -1){
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

    public void allList(){
        tableMaterialView.setItems(carp.getListMaterials());
    }
    public void melaminasList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass().equals(MaterialClass.Melaminas.toString()));
        tableMaterialView.setItems(selectedClass);
    }
    public void fibrosList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass().equals(MaterialClass.Fibros.toString()));
        tableMaterialView.setItems(selectedClass);
    }
    public void tapacantoList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass().equals(MaterialClass.Tapacantos.toString()));
        tableMaterialView.setItems(selectedClass);
    }
    public void varillasList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass().equals(MaterialClass.Varillas.toString()));
        tableMaterialView.setItems(selectedClass);
    }
    public void otrosList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass().equals(MaterialClass.Otros.toString()));
        tableMaterialView.setItems(selectedClass);
    }


    public void loadComboboxMaterial(){
        if(!materialClassComboBox.getItems().contains(MaterialClass.Fibros)
                || !materialClassComboBox.getItems().contains(MaterialClass.Melaminas)
                || !materialClassComboBox.getItems().contains(MaterialClass.Tapacantos)
                || !materialClassComboBox.getItems().contains(MaterialClass.Varillas)
                || !materialClassComboBox.getItems().contains(MaterialClass.Otros)) {
            materialClassComboBox.getItems().addAll(MaterialClass.values());
        }
    }


    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = loader.load();
        SceneController sceneController = loader.getController();
        sceneController.setStage(stg);

        stg.setScene(new Scene(root));
        stg.show();
    }

    public void clickEditGroup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditMaterialClass.fxml"));
        Parent root = loader.load();
        EditMaterialClassController editMaterialClassController = loader.getController();
        editMaterialClassController.setStage(stg);

        stg.setScene(new Scene(root));
        stg.show();
    }

    @FXML
    private void clickDelete() {
        boolean bool = alerts.showAlertDelete();
        if(bool){
            Material selected = tableMaterialView.getSelectionModel().getSelectedItem();
            carp.deleteMaterial(selected);
            json.saveCarpentryJson(carp);
            System.out.print("borrado");
            UpdateTable();
        }
    }


    @FXML
    private void clickAdd() {
        try {
            Material newMat = new Material(materialNameTextField.getText(), (Integer.parseInt(materialCostTextField.getText())),idMaterial(),materialClassComboBox.getValue().toString());

            carp.newMaterial(newMat);
            tableMaterialView.setItems(carp.getListMaterials());
            json.saveCarpentryJson(carp);
            materialNameTextField.clear();
            materialCostTextField.clear();
            UpdateTable();
        } catch (Exception e) {
            if (materialNameTextField.getText().equals("") || materialNameTextField.getText().isBlank()) {
                alerts.showAlertNameError();
            }else if (materialCostTextField.getText().isBlank() || materialCostTextField.getText().contains("[a-zA-Z]+")) {
                alerts.showAlertCostError();
            }else if (materialClassComboBox.getValue() == null)
                alerts.showAlertCategoryError();
        }
    }

    public Integer idMaterial(){
        int newId = 0;
        if(carp != null){
            for (Material mat : carp.getListMaterials()){
                newId = mat.getId()+1;
            }
        }
        else
            return 0;
        return newId;
    }

    @FXML
    private void clickCancel(){
        materialCostTextField.clear();
        materialNameTextField.clear();
    }


    public void getCarp()
    {
        Carpentry people = new Carpentry("sadasd","asdasd");
        //people.newMaterial(new Material("Frank",200,0,"Melaminas"));
       // people.newMaterial(new Material("Rebecca",300,1,"Melaminas"));
        //people.newMaterial(new Material("Mr.",400,2,"Melaminas"));
        json.saveCarpentryJson(people);

    }
}
