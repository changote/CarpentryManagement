package Carpentry.Materials;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Material {
    private StringProperty name;
    private IntegerProperty costPrice;
    private StringProperty materialClass;
    private IntegerProperty id;

    public Material(String name, Integer costPrice, Integer id, String materialClass) {
        this.id = new SimpleIntegerProperty(id);
        this.name =  new SimpleStringProperty(name);
        this.costPrice = new SimpleIntegerProperty(costPrice);
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

    public void setCostPrice(Integer costPrice) {
        this.costPrice.set(costPrice);
    }

    public String getName() {
        return this.name.get();
    }

    public Integer getCostPrice() {
        return this.costPrice.get();
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
