import java.util.List;

public class Rabbit extends Animal {

    public Rabbit(Field field, Location location) {
        super(field, location);
    }

    @Override
    public void act(List<Animal> newAnimals) {
    if (isAlive()) {
        Location newLocation = getField().freeAdjacentLocation(getLocation());
        if (newLocation != null) {
            setLocation(newLocation);
            System.out.println("Rabbit moved to: " + newLocation.getRow() + "," + newLocation.getCol());
        } else {
            setDead();
            System.out.println("Rabbit died at: " + getLocation().getRow() + "," + getLocation().getCol());
            }
        }
    }
}
