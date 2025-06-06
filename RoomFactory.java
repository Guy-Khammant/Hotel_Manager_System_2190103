public class RoomFactory{
   public Room createRoom(String type, String roomNumber) {
        switch (type.toLowerCase()) {
            case "single": return new SingleRoom(roomNumber);
            case "double": return new DoubleRoom(roomNumber);
            case "suite": return new SuiteRoom(roomNumber);
            default: throw new IllegalArgumentException("Invalid room type");
        }
    }
}