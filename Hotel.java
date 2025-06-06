import java.util.*;

public class Hotel implements Subject {
    private List<Room> rooms = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public Room getAvailableRoom(String type) {
        for (Room room : rooms) {
            if (!room.isBooked() && room.getType().equalsIgnoreCase(type)) {
                return room;
            }
        }
        return null;
    }

    public void bookRoom(Customer customer, String type) {
        Room room = getAvailableRoom(type);
        if (room == null) {
            System.out.println("No available " + type + " room."+ '\n' + "------------------------------------");
            return;
        }
        room.book();
        Key key = KeyManager.getInstance().generateKey(room);
        customer.assignRoom(room, key);
        notifyObservers(room.getPrice());
        room.setCustomer(customer);
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(double amount) {
        for (Observer o : observers) {
            o.update(amount);
        }
    }
}
