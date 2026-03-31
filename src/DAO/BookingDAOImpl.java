package DAO;

import model.Booking;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean insert(Booking booking) {
        return false;
    }

    @Override
    public List<Booking> getBookingsByPC(int pcId) {
        return new ArrayList<>();
    }

    @Override
    public boolean isAvailable(int pcId, java.time.LocalDateTime start, java.time.LocalDateTime end) {
        return true;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Booking booking = new Booking();

                booking.setId(rs.getInt("id"));
                booking.setPcId(rs.getInt("pc_id"));
                booking.setUserId(rs.getInt("user_id"));

                // xử lý null an toàn
                Timestamp start = rs.getTimestamp("start_time");
                booking.setStartTime(start != null ? start.toLocalDateTime() : null);

                Timestamp end = rs.getTimestamp("end_time");
                booking.setEndTime(end != null ? end.toLocalDateTime() : null);

                // tránh null status
                String status = rs.getString("status");
                booking.setStatus(status != null ? status : "UNKNOWN");

                list.add(booking);
            }

        } catch (Exception e) {
            System.out.println(" Lỗi khi lấy danh sách booking!");
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status=? WHERE id=?";
        try {
            try (Connection conn = DBConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, status);
                ps.setInt(2, bookingId);
                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createBooking(int pcId, int userId) {
        String sql = "INSERT INTO bookings(pc_id, user_id, start_time, status) VALUES (?, ?, NOW(), 'PENDING')";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pcId);
            ps.setInt(2, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existsBooking(int bookingId) {
        String sql = "SELECT 1 FROM bookings WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("pc_id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime(),
                        rs.getDouble("total_price"),
                        rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}