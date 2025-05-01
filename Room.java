public class Room {
    protected final String name;
    protected final String[] neighbors;
    protected final Area area;

    public Room(String name, String[] neighbors, Area area) {
        this.name = name;
        this.neighbors = neighbors;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String[] getNeighbors() {
        return neighbors;
    }
}
