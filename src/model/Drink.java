package model;

public class Drink {
    private int drinkId;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Drink() {
    }
    
    // Constructor đầy đủ
    public Drink(int drinkId, String name, String description, double price, int stock) {
        this.drinkId = drinkId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Drink(String name, String desc, double price, int stock) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.stock = stock;
    }

    // Getter & Setter
    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
