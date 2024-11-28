public class Plant extends LivingBeing {
    private String type;

    public Plant(String name, String type) {
        super(name);
        this.type = type;
    }

    @Override
    public void grow() {
        if (type.equals("Flowering")) {
            System.out.println(getName() + " is growing by producing flowers.");
        } else if (type.equals("NonFlowering")) {
            System.out.println(getName() + " is growing without producing flowers.");
        }
    }

    public void photosynthesize() {
        if (type.equals("Flowering")) {
            System.out.println(getName() + " is photosynthesizing while producing flowers.");
        } else if (type.equals("NonFlowering")) {
            System.out.println(getName() + " is photosynthesizing without flowers.");
        }
    }
}
