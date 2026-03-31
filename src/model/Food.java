package model;

public class Food {
    private int foodId;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Food() {
    }

    // Constructor đầy đủ
    public Food(int foodId, String name, String description, double price, int stock) {
        this.foodId = foodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Constructor không có foodId (tự tăng ID hoặc DB tự sinh)
    public Food(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Getter & Setter
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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