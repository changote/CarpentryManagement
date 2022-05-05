package Carpentry.Materials;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Material {
    private StringProperty name;
    private double costPrice;
    private StringProperty materialClass;
    private IntegerProperty id;

    public Material(String name, double costPrice, Integer id, String materialClass) {
        this.id = new SimpleIntegerProperty(id);
        this.name =  new SimpleStringProperty(name);
        this.costPrice = costPrice;
        this.materialClass = new SimpleStringProperty(materialClass);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMaterialClass() {
        return this.materialClass.get();
    }

    public void setMaterialClass(String materialClass) {
        this.materialClass.set(materialClass);
    }

    public void setCostPrice(double costPrice) {
        this.costPrice=costPrice;
    }

    public String getName() {
        return this.name.get();
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", costPrice=" + costPrice +
                '}';
    }

}
