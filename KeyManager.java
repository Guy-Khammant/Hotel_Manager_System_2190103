import java.util.*;

public class KeyManager {
    private static KeyManager instance;
    private Map<String, Key> keys;
    private KeyManager() {
        keys = new HashMap<String, Key>();
    }
    public static KeyManager getInstance() {
        if (instance == null) {
            instance = new KeyManager();
        }
        return instance;
    }
    public Key generateKey(Room room) {
        String keyCode = UUID.randomUUID().toString().substring(0, 8);
        Key key = new Key(keyCode, room.getRoomNumber());
        keys.put(room.getRoomNumber(), key);
        return key;
    }
    public Key getKey(String roomNumber) {
        return keys.get(roomNumber);
    }
}
