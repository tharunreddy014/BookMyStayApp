import java.util.*;

/**
 * UseCase6RoomAllocationService
 * 
 * This class demonstrates booking confirmation and safe room allocation
 * using Queue, Set, and HashMap to prevent double-booking.
 * 
 * @author tharunreddy014
 * @version 6.0
 */

// Reservation class (same as Use Case 5)
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
}

// Inventory Service (centralized state)
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        int current = getAvailability(roomType);
        if (current > 0) {
            inventory.put(roomType, current - 1);
        }
    }
}

// Booking Service (core allocation logic)
class BookingService {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    // Track allocated room IDs
    private Set<String> allocatedRoomIds;

    // Map room type -> allocated room IDs
    private Map<String, Set<String>> allocationMap;

    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocatedRoomIds = new HashSet<>();
        this.allocationMap = new HashMap<>();
    }

    // Process booking requests
    public void processBookings() {

        System.out.println("\n--- Processing Booking Requests ---\n");

        while (!queue.isEmpty()) {

            Reservation request = queue.poll();
            String roomType = request.getRoomType();

            // Check availability
            if (inventory.getAvailability(roomType) > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(roomType);

                // Ensure uniqueness using Set
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    // Add to allocation map
                    allocationMap
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                    // Update inventory immediately
                    inventory.decrementRoom(roomType);

                    // Confirm booking
                    System.out.println("Booking CONFIRMED");
                    System.out.println("Guest : " + request.getGuestName());
                    System.out.println("Room  : " + roomType);
                    System.out.println("Room ID : " + roomId);
                    System.out.println("------------------------------------");

                } else {
                    System.out.println("Duplicate Room ID detected! Skipping...");
                }

            } else {
                System.out.println("Booking FAILED (No Availability)");
                System.out.println("Guest : " + request.getGuestName());
                System.out.println("Room  : " + roomType);
                System.out.println("------------------------------------");
            }
        }
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v6.0     ");
        System.out.println("====================================");

        // Initialize queue (FIFO)
        Queue<Reservation> queue = new LinkedList<>();

        queue.offer(new Reservation("Alice", "Single Room"));
        queue.offer(new Reservation("Bob", "Single Room"));
        queue.offer(new Reservation("Charlie", "Single Room")); // should fail
        queue.offer(new Reservation("David", "Suite Room"));

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking service
        BookingService bookingService = new BookingService(queue, inventory);

        // Process bookings
        bookingService.processBookings();

        System.out.println("\nAll booking requests processed.");
    }
}
