public class Area {
    private final int x, y, width, height;

    public Area(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static Area getScaledArea(Area area, double scaleX, double scaleY) {
        int newX = (int) (area.getX() * scaleX);
        int newY = (int) (area.getY() * scaleY);
        int newWidth = (int) (area.getWidth() * scaleX);
        int newHeight = (int) (area.getHeight() * scaleY);

        return new Area(newX, newY, newWidth, newHeight);
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
}
