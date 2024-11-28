import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Field {

    private Object[][] field;

    public Field(int depth, int width) {
        field = new Object[depth][width];
    }

    public int getDepth() {
        return field.length;
    }

    public int getWidth() {
        return field[0].length;
    }

    public void clear(Location location) {
        field[location.getRow()][location.getCol()] = null;
    }

    public void place(Object object, Location location) {
        field[location.getRow()][location.getCol()] = object;
    }

    public Object getObjectAt(Location location) {
        return field[location.getRow()][location.getCol()];
    }

    public Location freeAdjacentLocation(Location location) {
        List<Location> free = new ArrayList<>();
        List<Location> adjacent = adjacentLocations(location);

        for (Location loc : adjacent) {
            if (getObjectAt(loc) == null) {
                free.add(loc);
            }
        }

        if (!free.isEmpty()) {
            Collections.shuffle(free);
            return free.get(0);
        }
        return null;
    }

    public List<Location> adjacentLocations(Location location) {
        List<Location> locations = new ArrayList<>();
        int row = location.getRow();
        int col = location.getCol();

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < field.length && c >= 0 && c < field[0].length && (r != row || c != col)) {
                    locations.add(new Location(r, c));
                }
            }
        }

        Collections.shuffle(locations);
        return locations;
    }
}
