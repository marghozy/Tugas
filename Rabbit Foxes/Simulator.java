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
    }
}
