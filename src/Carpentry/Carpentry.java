package Carpentry;


import Carpentry.Materials.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public class Carpentry {
    private String name;
    private String address;
    private final ObservableList<Material> listMaterial;
    private final ObservableList<Article> listArticle;
    private final ObservableList<Budget> listBudget;
    private double percentageWorkForce;

    public Carpentry(String name,String address){
        this.name = name;
        this.address = address;
        this.listMaterial = FXCollections.observableArrayList();
        this.listArticle = FXCollections.observableArrayList();
        this.listBudget = FXCollections.observableArrayList();
    }

    public ObservableList<Material> getListMaterials() {
        return listMaterial;
    }

    public ObservableList<Budget> getListBudget() {
        return listBudget;
    }

    public ObservableList<Article> getListArticle() {
        return listArticle;
    }

    public void deleteMaterial(Material selected) {
        this.listMaterial.remove(selected);
    }

    public void newMaterial(Material newMat) {
        this.listMaterial.add(newMat);
    }

    public double getPercentageWorkForce() {
        return percentageWorkForce;
    }

    public void refreshFinalPrice(){
        for(Article art : getListArticle()){
            art.setFinalPrice(art.getCostPrice()*(getPercentageWorkForce()+1));
        }
    }

    public void mostrarFinal(){
        for(Article art : getListArticle()){
            System.out.print(" / "+art.getFinalPrice());
        }
    }
    public void setPercentageWorkForce(double percentageWorkForce) {
        this.percentageWorkForce = percentageWorkForce;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void deleteArticle(Article selected) {
        this.listArticle.remove(selected);
    }

    public void newArticle(Article newArt) {
        this.listArticle.add(newArt);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addArticle(Article art){
        listArticle.add(art);
    }

    public void mostrarMaterial(){
        for(Material mat:listMaterial){
            System.out.print(mat.toString());
        }
        System.out.print("\n");
    }


}
