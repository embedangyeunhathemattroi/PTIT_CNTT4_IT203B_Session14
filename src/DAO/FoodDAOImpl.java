package DAO;

import model.Food;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAOImpl implements FoodDAO {

    // 1 INSERT FOOD
    @Override
    public boolean insert(Food food) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO foods(name, description, price, stock) VALUES (?, ?, ?, ?)";

        try {
            conn = DBConnection.getConnection(); // phải trả về java.sql.Connection
            ps = conn.prepareStatement(sql);

            ps.setString(1, food.getName());
            ps.setString(2, food.getDescription());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateStatus(int foodId, String status) {
        String sql = "UPDATE foods SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, foodId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Food> findAll() {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM foods";
        try (Connection conn = DBConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Food food = new Food(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"));
                list.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // FIND FOOD BY ID
    @Override
    public Food findById(int id) {
        String sql = "SELECT * FROM foods WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE FOOD
    @Override
    public boolean update(Food food) {
        String sql = "UPDATE foods SET name=?, description=?, price=?, stock=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, food.getName());
            ps.setString(2, food.getDescription());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getStock());
            ps.setInt(5, food.getFoodId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE FOOD
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM foods WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // FIND PENDING FOOD (nếu cần status)
    @Override
    public List<Food> findPending() {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE status='PENDING'";
        try (Connection conn = DBConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ADD FOOD TO BOOKING
    @Override
    public boolean addBookingFood(int bookingId, int foodId, int quantity) {
        String sql = "INSERT INTO booking_food(booking_id, food_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ps.setInt(2, foodId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET PENDING BOOKING FOODS
    @Override
    public List<String> getPendingBookingFoods() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT bf.id, f.name, bf.quantity " +
                "FROM booking_food bf JOIN foods f ON bf.food_id=f.id " +
                "WHERE bf.order_status='PENDING'";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String row = "ID: " + rs.getInt("id") +
                        " | Món: " + rs.getString("name") +
                        " | SL: " + rs.getInt("quantity");
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Utility: map ResultSet → Food
    private Food mapResultSet(ResultSet rs) throws SQLException {
        Food food = new Food();
        food.setFoodId(rs.getInt("id"));
        food.setName(rs.getString("name"));
        food.setDescription(rs.getString("description"));
        food.setPrice(rs.getDouble("price"));
        food.setStock(rs.getInt("stock"));
        return food;
    }
}