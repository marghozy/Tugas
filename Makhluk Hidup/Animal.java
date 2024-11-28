public class Animal extends LivingBeing {
    private String type;

    public Animal(String name, String type) {
        super(name);
        this.type = type;
    }

    @Override
    public void grow() {
        if (type.equals("Dog")) {
            System.out.println(getName() + " is growing by eating and exercising.");
        } else if (type.equals("Bird")) {
            System.out.println(getName() + " is growing by eating seeds.");
        }
    }

    public void move() {
        if (type.equals("Dog")) {
            System.out.println(getName() + " is running on four legs.");
        } else if (type.equals("Bird")) {
            System.out.println(getName() + " is flying in the sky.");
        }
    }
}
