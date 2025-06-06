public class DoubleRoom extends Room{
    public DoubleRoom(String RoomNumber){
        super(1500.0, RoomNumber);
    }
    @Override
    public String getType(){
        return "Double";
    }
}