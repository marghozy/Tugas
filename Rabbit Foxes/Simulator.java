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
            System.out.println("Step: " + step);
            printField();

            List<Animal> newAnimals = new ArrayList<>();
            for (Animal animal : animals) {
                animal.act(newAnimals);
            }
            animals.addAll(newAnimals);

            animals.removeIf(animal -> !animal.isAlive());
        }
    }

    private void populate() {
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                double rand = Math.random();
                Location location = new Location(row, col);
                if (rand < 0.1) {
                    Fox fox = new Fox(field, location);
                    animals.add(fox);
                    field.place(fox, location);
                } else if (rand < 0.3) {
                    Rabbit rabbit = new Rabbit(field, location);
                    animals.add(rabbit);
                    field.place(rabbit, location);
                }
            }
        }
    }

    private void printField() {
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Object obj = field.getObjectAt(new Location(row, col));
                if (obj instanceof Fox) {
                    System.out.print("F ");
                } else if (obj instanceof Rabbit) {
                    System.out.print("R ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
