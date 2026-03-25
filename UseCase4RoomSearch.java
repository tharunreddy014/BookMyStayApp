import java.util.HashMap;
import java.util.Map;

/**
 * UseCase4RoomSearch
 * 
 * This class demonstrates read-only room search functionality
 * using centralized inventory without modifying system state.
 * 
 * @author tharunreddy014
 * @version 4.0
 */

// Abstract Room class
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// Concrete Room Types
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000.0);
    }
}

// Inventory Class (Read-only access here)
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 0); // intentionally unavailable
        inventory.put("Suite Room", 2);
    }

    // Read-only method
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service (Read-only logic)
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("\n--- Available Rooms ---\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Defensive check: only show available rooms
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println("------------------------------------");
            }
        }
    }
}

// Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v4.0     ");
        System.out.println("====================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects
        Room[] rooms = {
            new SingleRoom(),
            new DoubleRoom(),
            new SuiteRoom()
        };

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform search (read-only operation)
        searchService.searchAvailableRooms(rooms);

        System.out.println("\nSearch completed successfully.");
    }
}
