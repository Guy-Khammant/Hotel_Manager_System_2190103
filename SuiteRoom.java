public class SuiteRoom extends Room{
    public SuiteRoom(String RoomNumber){
        super(2000.0, RoomNumber);
    }
    @Override
    public String getType(){
        return "Suite";
    }
}