public class Main {
    public static void main(String[] args) {

        int depth = 10;
        int width = 10;

        Simulator simulator = new Simulator(depth, width);

        int steps = 100;
        simulator.simulate(steps);
    }
}
