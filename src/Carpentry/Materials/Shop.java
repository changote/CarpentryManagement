package Carpentry.Materials;

import java.util.ArrayList;

public class Shop {

    private ArrayList<Article> articleList;
    private ArrayList<Material> materialList;
    private String name;

    public Shop(String name) {
        this.articleList = new ArrayList<Article>(articleList);
        this.materialList = new ArrayList<Material>(materialList);
        this.name = name;
    }

    public void newArticle(Article art){
        articleList.add(art);
    }

    public void newMaterial(Material mat){
        materialList.add(mat);
    }

    public ArrayList<Article> getArticleList() {
        return articleList;
    }

    public ArrayList<Material> getMaterialList() {
        return materialList;
    }

    public String getName() {
        return name;
    }
}
