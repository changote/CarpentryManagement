package Carpentry.Materials;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Budget {
    private String name;
    private float totalPrice;
    private ObservableList<Material> materialList;

    public Budget(String name, float totalPrice) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.materialList = FXCollections.observableArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ObservableList<Material> getListMaterials() {
        return materialList;
    }

    public void newMaterial(Material newMat) {
        this.materialList.add(newMat);
    }
}
