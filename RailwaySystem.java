package project;
import java.util.*;

class Train {
    String trainNo;
    String trainName;
    int seats;

    Train(String trainNo, String trainName, int seats) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.seats = seats;
    }
}

class Ticket {
    String ticketId;
    String passengerName;
    String trainNo;

    Ticket(String ticketId, String passengerName, String trainNo) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.trainNo = trainNo;
    }
}

public class RailwaySystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {

        // Default Train Data
        trains.add(new Train("101", "Chennai Express", 3));
        trains.add(new Train("202", "Bangalore Mail", 2));
        trains.add(new Train("303", "Mumbai Express", 4));

        while (true) {
            System.out.println("\n====== RAILWAY TICKET BOOKING SYSTEM ======");
            System.out.println("1. View Train List");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Check Seat Availability");
            System.out.println("5. View All Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewTrains();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    checkAvailability();
                    break;
                case 5:
                    viewTickets();
                    break;
                case 6:
                    System.out.println("Thank you! Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // 1. View Trains
    static void viewTrains() {
        System.out.println("\n--- Train List ---");
        for (Train t : trains) {
            System.out.println(t.trainNo + " - " + t.trainName + " | Seats Available: " + t.seats);
        }
    }

    // Find Train
    static Train findTrain(String trainNo) {
        for (Train t : trains) {
            if (t.trainNo.equals(trainNo)) {
                return t;
            }
        }
        return null;
    }

    // 2. Book Ticket
    static void bookTicket() {
        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();

        Train t = findTrain(trainNo);

        if (t == null) {
            System.out.println("Train Not Found!");
            return;
        }

        if (t.seats <= 0) {
            System.out.println("No Seats Available!");
            return;
        }

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        String ticketId = "T" + (tickets.size() + 1); // Auto Ticket ID

        tickets.add(new Ticket(ticketId, name, trainNo));
        t.seats--;

        System.out.println("Ticket Booked Successfully!");
        System.out.println("Your Ticket ID: " + ticketId);
    }

    // 3. Cancel Ticket
    static void cancelTicket() {
        System.out.print("Enter Ticket ID: ");
        String ticketId = sc.nextLine();

        Ticket removeTicket = null;

        for (Ticket tk : tickets) {
            if (tk.ticketId.equals(ticketId)) {
                removeTicket = tk;
                break;
            }
        }

        if (removeTicket == null) {
            System.out.println("Ticket Not Found!");
            return;
        }

        Train t = findTrain(removeTicket.trainNo);
        if (t != null) {
            t.seats++;
        }

        tickets.remove(removeTicket);

        System.out.println("Ticket Cancelled Successfully!");
    }

    // 4. Check Seat Availability
    static void checkAvailability() {
        System.out.print("Enter Train Number: ");
        String no = sc.nextLine();

        Train t = findTrain(no);

        if (t != null) {
            System.out.println("Train: " + t.trainName);
            System.out.println("Seats Available: " + t.seats);
        } else {
            System.out.println("Train Not Found!");
        }
    }

    // 5. View All Tickets
    static void viewTickets() {
        System.out.println("\n--- All Booked Tickets ---");

        if (tickets.isEmpty()) {
            System.out.println("No Tickets Booked!");
            return;
        }

        for (Ticket tk : tickets) {
            System.out.println("Ticket ID: " + tk.ticketId +
                    " | Passenger: " + tk.passengerName +
                    " | Train No: " + tk.trainNo);
        }
    }
}
