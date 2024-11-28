import java.util.List;

public class Foxes extends Animal {

    public Foxes(Field field, Location location) {
        super(field, location);
    }

    @Override
    public void act(List<Animal> newAnimals) {
    if (isAlive()) {
        Location newLocation = findFood();
        if (newLocation == null) {
            newLocation = getField().freeAdjacentLocation(getLocation());
        }

        if (newLocation != null) {
            setLocation(newLocation);
            System.out.println("Fox moved to: " + newLocation.getRow() + "," + newLocation.getCol());
        } else {
            setDead();
            System.out.println("Fox died at: " + getLocation().getRow() + "," + getLocation().getCol());
            }
        }
    }

    private Location findFood() {
        List<Location> adjacent = getField().adjacentLocations(getLocation());

        for (Location loc : adjacent) {
            Object animal = getField().getObjectAt(loc);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                rabbit.setDead();
                return loc;
            }
        }

        return null;
    }
}
