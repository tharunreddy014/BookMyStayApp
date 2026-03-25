import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 * 
 * This class demonstrates centralized room inventory management
 * using HashMap to maintain a single source of truth.
 * 
 * @author tharunreddy014
 * @version 3.1
 */

// Inventory Management Class
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (increase or decrease)
    public void updateAvailability(String roomType, int countChange) {
        int current = getAvailability(roomType);
        inventory.put(roomType, current + countChange);
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type : " + entry.getKey());
            System.out.println("Available : " + entry.getValue());
            System.out.println("------------------------------------");
        }
    }
}

// Main Application Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v3.1     ");
        System.out.println("====================================");

        // Initialize inventory (Single Source of Truth)
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Simulate updates
        System.out.println("\nUpdating inventory...\n");

        inventory.updateAvailability("Single Room", -2); // 2 rooms booked
        inventory.updateAvailability("Double Room", +1); // 1 room added

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated successfully.");
    }
}
