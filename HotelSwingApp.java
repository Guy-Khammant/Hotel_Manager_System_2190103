import java.awt.*;
import javax.swing.*;

public class HotelSwingApp {
    private Hotel hotel = new Hotel();
    private IncomeTracker incomeTracker = new IncomeTracker();
    private RoomFactory factory = new RoomFactory();

    private JTextArea outputArea;
    private JComboBox<String> roomTypeCombo;
    private JTextField roomNumberField;
    private JTextField customerNameField;
    private JComboBox<String> bookingTypeCombo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelSwingApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        hotel.addObserver(incomeTracker);

        JFrame frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel addRoomPanel = new JPanel(new FlowLayout());
        roomTypeCombo = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        roomNumberField = new JTextField(5);
        JButton addRoomButton = new JButton("Add Room");

        addRoomPanel.add(new JLabel("Room Type:"));
        addRoomPanel.add(roomTypeCombo);
        addRoomPanel.add(new JLabel("Room Number:"));
        addRoomPanel.add(roomNumberField);
        addRoomPanel.add(addRoomButton);

        JPanel bookingPanel = new JPanel(new FlowLayout());
        customerNameField = new JTextField(10);
        bookingTypeCombo = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        JButton bookButton = new JButton("Book Room");

        bookingPanel.add(new JLabel("Customer Name:"));
        bookingPanel.add(customerNameField);
        bookingPanel.add(new JLabel("Room Type:"));
        bookingPanel.add(bookingTypeCombo);
        bookingPanel.add(bookButton);

        JPanel extraPanel = new JPanel(new FlowLayout());
        JButton showAvailableButton = new JButton("Show Available Rooms");
        JButton showIncomeButton = new JButton("Show Total Income");
        JButton showAllRoomsButton = new JButton("Show All Room Status");
        JButton showInfoButton = new JButton("Show Information");
        extraPanel.add(showAvailableButton);
        extraPanel.add(showIncomeButton);
        extraPanel.add(showAllRoomsButton);
        extraPanel.add(showInfoButton);

        outputArea = new JTextArea(17, 60);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        panel.add(addRoomPanel);
        panel.add(bookingPanel);
        panel.add(extraPanel);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);

        addRoomButton.addActionListener(e -> addRoom());
        bookButton.addActionListener(e -> bookRoom());

        showAvailableButton.addActionListener(e -> showAvailableRooms());
        showIncomeButton.addActionListener(e -> showOutput("Total income: $" + incomeTracker.getTotalIncome()));
        showAllRoomsButton.addActionListener(e -> showAllRoomStatus());
        showInfoButton.addActionListener(e -> showInformation());
    }

    private void addRoom() {
        String type = (String) roomTypeCombo.getSelectedItem();
        String number = roomNumberField.getText();

        if (number.isEmpty()) {
            showOutput("Room number is required.");
            return;
        }

        boolean exists = hotel.getAllRooms().stream().anyMatch(r -> r.getRoomNumber().equals(number));
        if (exists) {
            showOutput("Room number already exists.");
            return;
        }

        try {
            hotel.addRoom(factory.createRoom(type, number));
            showOutput("Added room " + number + " of type " + type + ".");
        } catch (Exception e) {
            showOutput("Failed to add room: " + e.getMessage());
        }
    }

    private void bookRoom(){
        String name = customerNameField.getText();
        String type = (String) bookingTypeCombo.getSelectedItem();
        if (name.isEmpty()) {
            showOutput("Customer name is required.");
            return;
        }

        boolean available = hotel.getAllRooms().stream().anyMatch(r -> r.getType().equalsIgnoreCase(type) && !r.isBooked());

        if (!available) {
            showOutput("No available " + type + " rooms.");
            return;
        }

        try {
            Customer customer = new Customer(name);
            hotel.bookRoom(customer, type);
            for (Room room : hotel.getAllRooms()) {
                if (room.isBooked() && room.getCustomer() == customer) {
                    showOutput(name + " successfully booked a " + type + " room. (Room Number: " + room.getRoomNumber() + ")");
                    break;
                }
            }
        } catch (Exception ex) {
            showOutput("Booking failed: " + ex.getMessage());
        }
    }

    private void showAvailableRooms() {
        showOutput("--- Available Rooms ---");
        for (String roomType : new String[]{"Single", "Double", "Suite"}) {
            StringBuilder line = new StringBuilder(roomType + ": ");
            for (Room room : hotel.getAllRooms()) {
                if (room.getType().equalsIgnoreCase(roomType) && !room.isBooked()) {
                    line.append(room.getRoomNumber()).append(" ");
                }
            }
            showOutput(line.toString());
        }
    }

    private void showAllRoomStatus() {
        showOutput("--- Room Status ---");
        for (Room room : hotel.getAllRooms()) {
            String status = room.isBooked() ? "Occupied" : "Available";
            showOutput("Room " + room.getRoomNumber() + " (" + room.getType() + ") - " + status);
        }
    }

    private void showInformation() {
        showOutput("--- Room Booking Information ---");
        KeyManager km = KeyManager.getInstance();
        for (Room room : hotel.getAllRooms()) {
            if (room.isBooked() && room.getCustomer() != null) {
                Key key = km.getKey(room.getRoomNumber());
                String keyCode = key != null ? key.getKeyCode() : "N/A";
                showOutput("Customer: " + room.getCustomer().getName() + " | Room: " + room.getRoomNumber() + " (" + room.getType() + ") | Key Code: " + keyCode);
            }
        }
    }

    private void showOutput(String message) {
        outputArea.append(message + "\n");
    }
}
