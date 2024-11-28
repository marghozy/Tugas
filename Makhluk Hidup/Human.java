public class Human extends LivingBeing {
    private String profession;

    public Human(String name, String profession) {
        super(name);
        this.profession = profession;
    }

    @Override
    public void grow() {
        if (profession.equals("Programmer")) {
            System.out.println(getName() + " is learning new programming languages.");
        } else if (profession.equals("Doctor")) {
            System.out.println(getName() + " is gaining experience through practice.");
        }
    }

    public void speak() {
        if (profession.equals("Programmer")) {
            System.out.println(getName() + " is explaining coding concepts.");
        } else if (profession.equals("Doctor")) {
            System.out.println(getName() + " is consulting with patients.");
        }
    }
}
