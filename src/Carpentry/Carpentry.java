package Carpentry;


import Carpentry.Materials.*;

import java.util.ArrayList;

public class Carpentry {
    private String name;
    private String address;
    private ArrayList<Material> listMaterial;
    private ArrayList<Article> listArticle;

    public Carpentry(String name,String address){
        this.name = name;
        this.address = address;
        this.listMaterial = new ArrayList<Material>();
        this.listArticle = new ArrayList<Article>();
    }

    public void addMaterial(Material mat){
        listMaterial.add(mat);
    }

    public void addArticle(Article art){
        listArticle.add(art);
    }




}
