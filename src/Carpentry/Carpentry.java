package Carpentry;


import Carpentry.Materials.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Carpentry {
    private String name;
    private String address;
    private ArrayList<Material> listMaterial;
    private ArrayList<Article> listArticle;

    public Carpentry(String name,String address){
        this.name = name;
        this.address = address;
        this.listMaterial = new ArrayList<>();
        this.listArticle = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Material> getListMaterial() {
        return listMaterial;
    }

    public ArrayList<Article> getListArticle() {
        return listArticle;
    }

    public void addMaterial(Material mat){
        listMaterial.add(mat);
    }

    public void addMat(Material mat) {
        listMaterial.add(mat);
    }

    public void addArticle(Article art){
        listArticle.add(art);
    }

    public void mostrarMaterial(){
        for(Material mat:listMaterial){
            System.out.print(mat.toString());
        }
    }


}
