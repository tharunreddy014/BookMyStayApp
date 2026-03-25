/**
 * UseCase2RoomInitialization
 * 
 * This class demonstrates basic room type modeling using abstraction,
 * inheritance, and polymorphism in a Hotel Booking System.
 * 
 * It initializes different room types and displays their availability.
 * 
 * @author tharunreddy014
 * @version 2.1
 */

// Abstract class representing a generic Room
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double price;

    // Constructor
    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    // Method to display room details
    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// Single Room class
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500.0);
    }
}

// Double Room class
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500.0);
    }
}

// Suite Room class
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000.0);
    }
}

// Main Application Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v2.1     ");
        System.out.println("====================================");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability (simple variables)
        int singleAvailable = 10;
        int doubleAvailable = 5;
        int suiteAvailable = 2;

        // Display room details and availability
        System.out.println("\n--- Room Details & Availability ---\n");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println("------------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println("------------------------------------");

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailable);
        System.out.println("------------------------------------");

        System.out.println("\nApplication terminated successfully.");
    }
}
