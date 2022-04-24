package Controller;

import Main.Main;
import java.io.IOException;

public class SceneController {

    Main m = new Main();

    public void ClickBack() throws IOException {
        m.changeScene("/fxml/Home.fxml");
    }
    public void ClickMaterial() throws IOException {
        m.changeScene("/fxml/Materials.fxml");
    }
    public void ClickArticle() throws IOException {
        m.changeScene("/fxml/Articles.fxml");
    }
}
