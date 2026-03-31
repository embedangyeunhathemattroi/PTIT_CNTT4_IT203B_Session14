package DAO;

import model.Booking;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Timestamp;

public interface BookingDAO {
    boolean insert(Booking booking);

    List<Booking> getBookingsByPC(int pcId);

    boolean isAvailable(int pcId, LocalDateTime start, LocalDateTime end);

    List<Booking> getAllBookings();

    boolean updateStatus(int bookingId, String status); // chỉ khai báo

    Booking findById(int id);

}