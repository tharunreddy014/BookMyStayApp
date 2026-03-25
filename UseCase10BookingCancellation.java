import java.util.*;

/**
 * UseCase10BookingCancellation
 * 
 * This class demonstrates safe booking cancellation using Stack (LIFO)
 * and controlled rollback of inventory and allocation state.
 * 
 * @author tharunreddy014
 * @version 10.0
 */

// Reservation class
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

    public String getRoomType() {
        return roomType;
    }

    public String getGuestName() {
        return guestName;
    }
}

// Inventory class
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public void incrementRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\n--- Updated Inventory ---\n");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking History (tracks active bookings)
class BookingHistory {

    private Map<String, Reservation> activeBookings;

    public BookingHistory() {
        activeBookings = new HashMap<>();
    }

    public void addReservation(Reservation res) {
        activeBookings.put(res.getReservationId(), res);
    }

    public Reservation getReservation(String reservationId) {
        return activeBookings.get(reservationId);
    }

    public void removeReservation(String reservationId) {
        activeBookings.remove(reservationId);
    }

    public boolean exists(String reservationId) {
        return activeBookings.containsKey(reservationId);
    }
}

// Cancellation Service
class CancellationService {

    private BookingHistory history;
    private RoomInventory inventory;

    // Stack for rollback tracking (released room IDs)
    private Stack<String> rollbackStack;

    public CancellationService(BookingHistory history, RoomInventory inventory) {
        this.history = history;
        this.inventory = inventory;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {

        System.out.println("\nProcessing cancellation for ID: " + reservationId);

        // Validate existence
        if (!history.exists(reservationId)) {
            System.out.println("Cancellation FAILED: Reservation not found.");
            return;
        }

        // Fetch reservation
        Reservation res = history.getReservation(reservationId);

        // Step 1: Push room ID to rollback stack
        rollbackStack.push(reservationId);

        // Step 2: Restore inventory
        inventory.incrementRoom(res.getRoomType());

        // Step 3: Remove from booking history
        history.removeReservation(reservationId);

        // Confirmation
        System.out.println("Cancellation SUCCESS");
        System.out.println("Guest : " + res.getGuestName());
        System.out.println("Room  : " + res.getRoomType());
        System.out.println("Rolled Back ID : " + reservationId);
        System.out.println("------------------------------------");
    }

    public void displayRollbackStack() {
        System.out.println("\n--- Rollback Stack (LIFO Order) ---\n");
        for (String id : rollbackStack) {
            System.out.println(id);
        }
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v10.0    ");
        System.out.println("====================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking history with confirmed bookings
        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("SI-12345", "Alice", "Single Room"));
        history.addReservation(new Reservation("DB-67890", "Bob", "Double Room"));

        // Initialize cancellation service
        CancellationService cancelService = new CancellationService(history, inventory);

        // Perform cancellations
        cancelService.cancelBooking("SI-12345"); // valid
        cancelService.cancelBooking("XX-00000"); // invalid

        // Display rollback stack
        cancelService.displayRollbackStack();

        // Show updated inventory
        inventory.displayInventory();

        System.out.println("\nSystem state restored successfully after cancellation.");
    }
}
