import java.util.*;

/**
 * UseCase8BookingHistoryReport
 * 
 * This class demonstrates booking history tracking and reporting
 * using List to maintain ordered records of confirmed reservations.
 * 
 * @author tharunreddy014
 * @version 8.0
 */

// Reservation class (confirmed booking)
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest Name     : " + guestName);
        System.out.println("Room Type      : " + roomType);
        System.out.println("------------------------------------");
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Retrieve all reservations
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display full booking history
    public void displayAllBookings() {
        System.out.println("\n--- Booking History ---\n");

        for (Reservation res : history.getAllReservations()) {
            res.display();
        }
    }

    // Generate summary report
    public void generateSummaryReport() {
        System.out.println("\n--- Booking Summary Report ---\n");

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation res : history.getAllReservations()) {
            String roomType = res.getRoomType();
            roomCount.put(roomType, roomCount.getOrDefault(roomType, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : roomCount.entrySet()) {
            System.out.println("Room Type : " + entry.getKey() +
                               " | Total Bookings : " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v8.0     ");
        System.out.println("====================================");

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from Use Case 6)
        history.addReservation(new Reservation("SI-12345", "Alice", "Single Room"));
        history.addReservation(new Reservation("SI-67890", "Bob", "Single Room"));
        history.addReservation(new Reservation("SU-54321", "Charlie", "Suite Room"));
        history.addReservation(new Reservation("DB-11111", "David", "Double Room"));

        // Initialize report service
        BookingReportService reportService = new BookingReportService(history);

        // Display full history
        reportService.displayAllBookings();

        // Generate summary report
        reportService.generateSummaryReport();

        System.out.println("\nReporting completed successfully.");
    }
}
