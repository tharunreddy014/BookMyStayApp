import java.util.*;

/**
 * UseCase7AddOnServiceSelection
 * 
 * This class demonstrates adding optional services to an existing reservation
 * without modifying core booking or inventory logic.
 * 
 * @author tharunreddy014
 * @version 7.0
 */

// Add-On Service class
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void displayService() {
        System.out.println("Service : " + serviceName + " | Cost : ₹" + price);
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap
            .computeIfAbsent(reservationId, k -> new ArrayList<>())
            .add(service);

        System.out.println("Added service '" + service.getServiceName() +
                "' to Reservation ID: " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {
        System.out.println("\n--- Services for Reservation ID: " + reservationId + " ---\n");

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (AddOnService service : services) {
            service.displayService();
        }
    }

    // Calculate total add-on cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;
        for (AddOnService service : services) {
            total += service.getPrice();
        }
        return total;
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v7.0     ");
        System.out.println("====================================");

        // Simulated reservation IDs (from Use Case 6)
        String reservation1 = "SI-12345";
        String reservation2 = "SU-67890";

        // Initialize service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservation1, new AddOnService("Breakfast", 300));
        manager.addService(reservation1, new AddOnService("WiFi", 100));
        manager.addService(reservation1, new AddOnService("Airport Pickup", 800));

        manager.addService(reservation2, new AddOnService("Spa Access", 1500));

        // Display services
        manager.displayServices(reservation1);
        double total1 = manager.calculateTotalCost(reservation1);
        System.out.println("Total Add-On Cost : ₹" + total1);

        manager.displayServices(reservation2);
        double total2 = manager.calculateTotalCost(reservation2);
        System.out.println("Total Add-On Cost : ₹" + total2);

        System.out.println("\nAdd-on services processed successfully.");
    }
}
