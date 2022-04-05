package Carpentry.Materials;

public class Material {
    private String name;
    private String costPrice;

    public Material(String name, String costPrice) {
        this.name = name;
        this.costPrice = costPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }
}
