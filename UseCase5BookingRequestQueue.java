import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 * 
 * This class demonstrates booking request handling using Queue
 * to ensure First-Come-First-Served (FIFO) processing.
 * 
 * No inventory updates are performed in this stage.
 * 
 * @author tharunreddy014
 * @version 5.0
 */

// Reservation class (represents a booking request)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type  : " + roomType);
    }
}

// Booking Queue class
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display all requests in order
    public void displayQueue() {
        System.out.println("\n--- Booking Request Queue (FIFO Order) ---\n");

        for (Reservation res : queue) {
            res.displayReservation();
            System.out.println("------------------------------------");
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v5.0     ");
        System.out.println("====================================");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("David", "Single Room"));

        // Display queue (FIFO order)
        bookingQueue.displayQueue();

        System.out.println("\nAll requests are queued and waiting for processing.");
    }
}
