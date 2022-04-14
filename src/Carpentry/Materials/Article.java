package Carpentry.Materials;

import java.util.ArrayList;

public class Article {
    private String name;
    private float costPrice;
    private float finalPrice;
    private ArrayList<Material> materialList;

    public Article(String name, float costprice, float finalprice, ArrayList<Material> materialslist) {
        this.name = name;
        this.costPrice = costprice;
        this.finalPrice = finalprice;
        this.materialList = new ArrayList<>(materialslist);
    }

    public void setName(String name) {
        this.name = name;
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
                ", /nmaterialslist=" + materialList +
                '}';
    }
}
