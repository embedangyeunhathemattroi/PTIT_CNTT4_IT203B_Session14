package model;

public class Ingredient {
    private String name;
    private int stock;

    public Ingredient() {
    }

    public Ingredient(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Trừ số lượng khi sử dụng
    public boolean use(int quantity) {
        if (quantity > stock)
            return false;
        stock -= quantity;
        return true;
    }
}