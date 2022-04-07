package Carpentry.Materials;

public class Material {
    private String name;
    private int costPrice;

    public Material(String name, int costPrice) {
        this.name = name;
        this.costPrice = costPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public String getName() {
        return name;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public Material editMaterial(Material material){

        return material;
    }
}
