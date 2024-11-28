public class Main {
    public static void main(String[] args) {
        // **1. Contoh untuk Human (Manusia)**
        // Menginisialisasi programmer dan doctor dengan tambahan parameter profession
        Human programmer = new Human("Alice", "Programmer");
        Human doctor = new Human("Bob", "Doctor");

        System.out.println("=== Human ===");
        programmer.breathe();
        programmer.grow();
        programmer.speak();

        doctor.breathe();
        doctor.grow();
        doctor.speak();

        // **2. Contoh untuk Animal (Hewan)**
        // Menginisialisasi objek Hewan dengan konstruktor yang sesuai (menambahkan type)
        Animal dog = new Animal("Buddy", "Dog");
        Animal bird = new Animal("Tweety", "Bird");

        System.out.println("\n=== Animal ===");
        dog.breathe();
        dog.grow();
        dog.move();

        bird.breathe();
        bird.grow();
        bird.move();

        // **3. Contoh untuk Plant (Tumbuhan)**
        // Menginisialisasi objek Plant dengan konstruktor yang membutuhkan type (Flowering atau NonFlowering)
        Plant floweringPlant = new Plant("Rose", "Flowering");
        Plant nonFloweringPlant = new Plant("Fern", "NonFlowering");

        System.out.println("\n=== Plant ===");
        floweringPlant.breathe();
        floweringPlant.grow();
        floweringPlant.photosynthesize();

        nonFloweringPlant.breathe();
        nonFloweringPlant.grow();
        nonFloweringPlant.photosynthesize();
    }
}
