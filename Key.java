public class Key {
    private String keyCode;
    private String roomNumber;

    public Key(String keyCode, String roomNumber) {
        this.keyCode = keyCode;
        this.roomNumber = roomNumber;
    }

    public String getKeyCode() { 
        return keyCode; 
    }

    public String getRoomNumber() { 
        return roomNumber; 
    }
}
