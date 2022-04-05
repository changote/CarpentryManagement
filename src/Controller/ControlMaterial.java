package Controller;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControlMaterial {
    @FXML
    private Button toMaterial;
    @FXML
    private Button toArticle;
    @FXML
    private Button toBudget;
    @FXML
    private Button goBack;


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
