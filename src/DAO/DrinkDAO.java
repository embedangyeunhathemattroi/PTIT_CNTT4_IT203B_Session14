package DAO;

import model.Drink;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO {

    // 1 INSERT DRINK
    public boolean insert(Drink drink) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO drinks(name, description, price, stock) VALUES (?, ?, ?, ?)";

        try {
            conn = DBConnection.getConnection(); // phải trả về java.sql.Connection
            ps = conn.prepareStatement(sql);

            ps.setString(1, drink.getName());
            ps.setString(2, drink.getDescription());
            ps.setDouble(3, drink.getPrice());
            ps.setInt(4, drink.getStock());

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

    public boolean updateStatus(int drinkId, String status) {
        String sql = "UPDATE drinks SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, drinkId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Drink> findAll() {
        List<Drink> list = new ArrayList<>();
        String sql = "SELECT * FROM drinks";
        try (Connection conn = DBConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Drink drink = new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"));
                list.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // FIND DRINK BY ID
    public Drink findById(int id) {
        String sql = "SELECT * FROM drinks WHERE id = ?";
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

    // UPDATE DRINK
    public boolean update(Drink drink) {
        String sql = "UPDATE drinks SET name=?, description=?, price=?, stock=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, drink.getName());
            ps.setString(2, drink.getDescription());
            ps.setDouble(3, drink.getPrice());
            ps.setInt(4, drink.getStock());
            ps.setInt(5, drink.getDrinkId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE DRINK
    public boolean delete(int id) {
        String sql = "DELETE FROM drinks WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // FIND PENDING DRINK (nếu cần status)
    public List<Drink> findPending() {
        List<Drink> list = new ArrayList<>();
        String sql = "SELECT * FROM drinks WHERE status='PENDING'";
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

    // ADD DRINK TO BOOKING
    public boolean addBookingDrink(int bookingId, int drinkId, int quantity) {
        String sql = "INSERT INTO booking_drink(booking_id, drink_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ps.setInt(2, drinkId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET PENDING BOOKING DRINKS
    public List<String> getPendingBookingDrinks() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT bd.id, d.name, bd.quantity " +
                "FROM booking_drink bd JOIN drinks d ON bd.drink_id=d.id " +
                "WHERE bd.order_status='PENDING'";

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



    // Utility: map ResultSet → Drink
    private Drink mapResultSet(ResultSet rs) throws SQLException {
        Drink drink = new Drink();
        drink.setDrinkId(rs.getInt("id"));
        drink.setName(rs.getString("name"));
        drink.setDescription(rs.getString("description"));
        drink.setPrice(rs.getDouble("price"));
        drink.setStock(rs.getInt("stock"));
        return drink;
    }

    public boolean isDrinkNameExists(String name) {
        String sql = "SELECT * FROM drinks WHERE name = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; // nếu có kết quả thì tên đã tồn tại
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}