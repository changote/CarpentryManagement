package Carpentry.Materials;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Article {
    private StringProperty name;
    private double costPrice;
    private IntegerProperty id;
    private double finalPrice;
    private StringProperty materialClass;

    public Article(String name, double costPrice, Integer id, String materialClass) {
        this.id = new SimpleIntegerProperty(id);
        this.name =  new SimpleStringProperty(name);
        this.costPrice = costPrice;
        this.materialClass = new SimpleStringProperty(materialClass);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return this.name.get();
    }

//
//    public void setPercentageWorkForce(double percentageWorkForce) {
//        this.percentageWorkForce = percentageWorkForce/10;
//    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public Double getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice=finalPrice;
    }


    public String getMaterialClass() {
        return this.materialClass.get();
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }


    public Integer getId() {
        return this.id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "Article:" +
                "/nname='" + name + '\'' +
                ", /ncostprice=" + costPrice +
                 ", /nfinalprice=" + finalPrice +
                '}';
    }
}
