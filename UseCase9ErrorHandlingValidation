import java.util.HashMap;
import java.util.Map;

/**
 * UseCase9ErrorHandlingValidation
 * 
 * This class demonstrates validation and error handling using
 * custom exceptions and fail-fast design principles.
 * 
 * @author tharunreddy014
 * @version 9.0
 */

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Class with validation
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    // Validate room type
    public void validateRoomType(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    // Check availability
    public void validateAvailability(String roomType) throws InvalidBookingException {
        int available = inventory.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No availability for room type: " + roomType);
        }
    }

    // Safe decrement
    public void decrementRoom(String roomType) throws InvalidBookingException {
        int current = inventory.get(roomType);

        if (current <= 0) {
            throw new InvalidBookingException("Cannot decrement. No rooms available for: " + roomType);
        }

        inventory.put(roomType, current - 1);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n--- Current Inventory ---\n");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking Service with validation
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(String guestName, String roomType) {

        try {
            // Fail-fast validation
            inventory.validateRoomType(roomType);
            inventory.validateAvailability(roomType);

            // If valid → proceed
            inventory.decrementRoom(roomType);

            System.out.println("Booking SUCCESS");
            System.out.println("Guest : " + guestName);
            System.out.println("Room  : " + roomType);
            System.out.println("------------------------------------");

        } catch (InvalidBookingException e) {
            // Graceful failure handling
            System.out.println("Booking FAILED");
            System.out.println("Reason : " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v9.0     ");
        System.out.println("====================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking service
        BookingService bookingService = new BookingService(inventory);

        // Test cases
        bookingService.processBooking("Alice", "Single Room");   // valid
        bookingService.processBooking("Bob", "Suite Room");     // no availability
        bookingService.processBooking("Charlie", "Deluxe Room"); // invalid type

        // Show inventory after operations
        inventory.displayInventory();

        System.out.println("\nSystem running safely after handling errors.");
    }
}
