package model;

public class BookingFood {
    private int id;
    private int bookingId;
    private int foodId;
    private int quantity;

    // Constructor không tham số
    public BookingFood() {
    }

    // Constructor đầy đủ
    public BookingFood(int id, int bookingId, int foodId, int quantity) {
        this.id = id;
        this.bookingId = bookingId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookingFood{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                '}';
    }
}