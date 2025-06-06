public class Customer {
    private String name;
    private Room bookedRoom;
    private Key key;

    public Customer(String name) {
        this.name = name;
    }

    public void assignRoom(Room room, Key key) {
        this.bookedRoom = room;
        this.key = key;
        System.out.println(name + " booked a " + room.getType() + " room.");
        System.out.println("Key assigned: " + key.getKeyCode());
    }

    public Room getBookedRoom() { return bookedRoom; }
    public String getName(){
        return name;
    }
}
