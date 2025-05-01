public class Room {
    protected final String name;
    protected final Room[] neighbors;

    // this might be problematic, we might need to initialize
    // with a list of room names, and then we can actually get
    // the neighbors in a getter method. A static Hashmap<String, Room>
    // might be helpful
    public Room(String name, Room[] neighbors) {
        this.name = name;
        this.neighbors = neighbors;
    }

    public String getName() {
        return name;
    }

    public Room[] getNeighbors() {
        return neighbors;
    }
}
