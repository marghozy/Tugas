public class Main {
    public static void main(String[] args) {

        Human programmer = new Programmer("Alice");
        Human doctor = new Doctor("Bob");

        System.out.println("=== Human ===");
        programmer.breathe();
        programmer.grow();
        programmer.speak();

        doctor.breathe();
        doctor.grow();
        doctor.speak();

        Animal dog = new Dog("Buddy");
        Animal bird = new Bird("Tweety");

        System.out.println("\n=== Animal ===");
        dog.breathe();
        dog.grow();
        dog.move();

        bird.breathe();
        bird.grow();
        bird.move();

        Plant floweringPlant = new FloweringPlant("Rose");
        Plant nonFloweringPlant = new NonFloweringPlant("Fern");

        System.out.println("\n=== Plant ===");
        floweringPlant.breathe();
        floweringPlant.grow();
        floweringPlant.photosynthesize();

        nonFloweringPlant.breathe();
        nonFloweringPlant.grow();
        nonFloweringPlant.photosynthesize();
    }
}
