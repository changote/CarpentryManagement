package Carpentry.Materials;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Material {
    private StringProperty name =  new SimpleStringProperty();
    private IntegerProperty costPrice;
    private MaterialClass materialClass;

    public Material(String name, Integer costPrice, MaterialClass materialClass) {
        this.name =  new SimpleStringProperty(name);
        this.costPrice = new SimpleIntegerProperty(costPrice);
        this.materialClass = materialClass;
    }



    public void setName(String name) {
        this.name.set(name);
    }

    public MaterialClass getMaterialClass() {
        return materialClass;
    }

    public void setMaterialClass(MaterialClass materialClass) {
        this.materialClass = materialClass;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice.set(costPrice);
    }

    public String getName() {
        return this.name.get();
    }

    public int getCostPrice() {
        return this.costPrice.get();
    }

    public Material editMaterial(Material material){

        return material;
    }



    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", costPrice=" + costPrice +
                '}';
    }
}
