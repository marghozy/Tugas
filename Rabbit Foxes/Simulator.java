import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private Field field;
    private List<Animal> animals;

    public Simulator(int depth, int width) {
        field = new Field(depth, width);
        animals = new ArrayList<>();
        populate();
    }

    public void simulate(int steps) {
        for (int step = 0; step < steps; step++) {
            List<Animal> newAnimals = new ArrayList<>();
            for (Animal animal : animals) {
                animal.act(newAnimals);
            }
            animals.addAll(newAnimals);
        }
    }

    private void populate() {
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (Math.random() < 0.1) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(field, location);
                    animals.add(rabbit);
                    field.place(rabbit, location);
                } else if (Math.random() < 0.05) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(field, location);
                    animals.add(fox);
                    field.place(fox, location);
                }
            }
        }
    }
}
