public class Room {
    private final String name;
    private final String[] neighbors;

    private final Area area;

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

    public Area getArea() {
        return area;
    }
}
