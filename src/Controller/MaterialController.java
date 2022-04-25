package Controller;

import Alert.Alerts;
import Carpentry.Carpentry;
import Carpentry.Materials.Material;
import Carpentry.Materials.MaterialClass;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import json.CarpentryJson;

public class MaterialController implements Initializable {
    //configure the table
    @FXML private TableView<Material> tableMaterialView;
    @FXML private TableColumn<Material, String> materialNameColumn;
    @FXML private TableColumn<Material, Integer> materialCostColumn;
    @FXML private TableColumn<Material, Integer> materialIdColumn;

    //These instance variables are used to create new Person objects
    @FXML private TextField materialNameTextField;
    @FXML private ComboBox<MaterialClass> materialClassComboBox;
    @FXML private TextField materialCostTextField;

    @FXML private TextField keywordTextField;

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
        //getCarp();

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
        materialCostColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        materialIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        materialIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        searchBarProduct(carp.getListMaterials());

    }
    public void onEditNameMaterialChanged(TableColumn.CellEditEvent<Material,String>materialStringCellEditEvent){
        Material mat = tableMaterialView.getSelectionModel().getSelectedItem();
        mat.setName(materialStringCellEditEvent.getNewValue());

        json.saveCarpentryJson(carp);

    }

    public void onEditCostMaterialChanged(TableColumn.CellEditEvent<Material,Integer>materialIntegerCellEditEvent){
        Material mat = tableMaterialView.getSelectionModel().getSelectedItem();
        mat.setCostPrice(materialIntegerCellEditEvent.getNewValue());

        json.saveCarpentryJson(carp);

    }

    public void searchBarProduct(ObservableList<Material> dataMaterial ){
        FilteredList<Material> filteredData = new FilteredList<>(dataMaterial, b -> true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredData.setPredicate(tableMaterialName -> {

                if(newValue.isEmpty() || newValue.isBlank()){
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

    public void allList(){
        tableMaterialView.setItems(carp.getListMaterials());
    }
    public void maderaList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass() == MaterialClass.Maderas);
        tableMaterialView.setItems(selectedClass);
    }
    public void herrajelist (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass() == MaterialClass.Herrajes);
        tableMaterialView.setItems(selectedClass);
    }
    public void tornilloList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass() == MaterialClass.Tornillos);
        tableMaterialView.setItems(selectedClass);
    }
    public void tapacantoList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass() == MaterialClass.Tapacantos);
        tableMaterialView.setItems(selectedClass);
    }
    public void otrosList (){
        FilteredList<Material> selectedClass = new FilteredList<>(carp.getListMaterials(), i-> i.getMaterialClass() == MaterialClass.Otros);
        tableMaterialView.setItems(selectedClass);
    }


    public void loadComboboxMaterial(){
        materialClassComboBox.getItems().addAll(MaterialClass.values());
    }


    public void ClickBack() throws IOException {
        scene.ClickBack();
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
            Material newMat = new Material(materialNameTextField.getText(), (Integer.parseInt(materialCostTextField.getText())),idMaterial(),materialClassComboBox.getValue());

            carp.newMaterial(newMat);
            tableMaterialView.setItems(carp.getListMaterials());
            json.saveCarpentryJson(carp);
            UpdateTable();
        } catch (Exception e) {
            if (materialNameTextField.getText().equals("") || materialNameTextField.getText().isBlank()) { ////////OBTIENE UN NOMBRE
                alerts.showAlertNameError();
            }else if (materialCostTextField.getText().equals("") || materialCostTextField.getText().chars().allMatch(Character::isWhitespace) || !materialCostTextField.getText().contains("[a-zA-Z]+")) {
                alerts.showAlertCostError(); ///////////OBTIENE UN NUMERO
            }

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


    /*public void getCarp(Carpentry carp)
    {
        Carpentry people = carp;
        people.newMaterial(new Material("Frank",200));
        people.newMaterial(new Material("Rebecca",300));
        people.newMaterial(new Material("Mr.",400));
        //json.saveCarpentryJson(people);

    }*/
}
