public class SingleRoom extends Room{
    public SingleRoom(String RoomNumber){
        super(1000.0, RoomNumber);
    }
    @Override
    public String getType(){
        return "Single";
    }
}