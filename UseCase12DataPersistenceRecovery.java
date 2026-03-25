import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery
 * 
 * This class demonstrates persistence using serialization and recovery
 * using deserialization for booking history and inventory.
 * 
 * @author tharunreddy014
 * @version 12.0
 */

// Reservation class (Serializable)
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

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
        System.out.println("ID : " + reservationId +
                " | Guest : " + guestName +
                " | Room : " + roomType);
    }
}

// Inventory class (Serializable)
class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void setInventory(Map<String, Integer> data) {
        this.inventory = data;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void display() {
        System.out.println("\n--- Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Wrapper class to persist full system state
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    List<Reservation> reservations;
    Map<String, Integer> inventory;

    public SystemState(List<Reservation> reservations, Map<String, Integer> inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save state
    public void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("\nSystem state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state
    public SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("\nSystem state loaded successfully.");
            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("\nNo saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nError loading state. Starting with safe defaults.");
        }
        return null;
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v12.0    ");
        System.out.println("====================================");

        PersistenceService persistence = new PersistenceService();

        // Try loading existing state
        SystemState state = persistence.load();

        List<Reservation> reservations;
        Map<String, Integer> inventoryMap;

        if (state == null) {
            // Fresh start
            reservations = new ArrayList<>();
            inventoryMap = new HashMap<>();

            inventoryMap.put("Single Room", 2);
            inventoryMap.put("Double Room", 1);

            // Add sample booking
            reservations.add(new Reservation("SI-101", "Alice", "Single Room"));

        } else {
            // Restore state
            reservations = state.reservations;
            inventoryMap = state.inventory;
        }

        // Display restored data
        System.out.println("\n--- Booking History ---");
        for (Reservation r : reservations) {
            r.display();
        }

        RoomInventory inventory = new RoomInventory();
        inventory.setInventory(inventoryMap);
        inventory.display();

        // Save state before exit
        persistence.save(new SystemState(reservations, inventoryMap));

        System.out.println("\nSystem recovery and persistence completed.");
    }
}
