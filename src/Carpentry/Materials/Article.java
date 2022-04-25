package Carpentry.Materials;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Article {
    private String name;
    private float costPrice;
    private float finalPrice;
    private ObservableList<Material> listMaterial;

    public Article(String name, float costprice, float finalprice, ArrayList<Material> materialslist) {
        this.name = name;
        this.costPrice = costprice;
        this.finalPrice = finalprice;
        this.listMaterial = FXCollections.observableArrayList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Material> getListMaterials() {
        return listMaterial;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Article editArticle(Article article){

        return article;
    }

    @Override
    public String toString() {
        return "Article:" +
                "/nname='" + name + '\'' +
                ", /ncostprice=" + costPrice +
                ", /nfinalprice=" + finalPrice +
                ", /nmaterialslist=" + listMaterial +
                '}';
    }
}
