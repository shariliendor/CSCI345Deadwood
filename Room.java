import java.util.HashMap;

public class Room {
    private final String name;
    private final String[] neighbors;
    private final Area area;
    private static HashMap<String, Room> roomNames = new HashMap<>();

    public Room(String name, String[] neighbors, Area area) {
        this.name = name;
        this.neighbors = neighbors;
        this.area = area;

        roomNames.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String[] getNeighbors() {
        return neighbors;
    }

    public Area getArea() {
        return area;
    }

    public static Room getRoom(String name) {
        return roomNames.get(name);
    }
}
