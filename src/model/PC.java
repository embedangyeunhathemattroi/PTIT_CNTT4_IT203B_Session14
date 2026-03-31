package model;

public class PC {
    private int id;
    private String name;
    private int categoryId; // 1 = VIP, 2 = Standard
    private String config;
    private double price;
    private String status;

    // Constructor rỗng (cho DAO hoặc tạo mặc định)
    public PC() {
    }

    // Constructor đầy đủ (có id)
    public PC(int id, String name, int categoryId, String config, double price, String status) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.config = config;
        this.price = price;
        this.status = status;
    }

    // Constructor không có id (tự gán khi add)
    public PC(String name, int categoryId, String config, double price, String status) {
        this.name = name;
        this.categoryId = categoryId;
        this.config = config;
        this.price = price;
        this.status = status;
    }

    // ================= Getters & Setters =================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPricePerHour() {
        return price;
    } // nếu cần
}