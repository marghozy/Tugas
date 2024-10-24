class Kucing extends Hewan {
    String ras;

    public Kucing(String nama, int umur, String ras) {
        super(nama, umur);
        this.ras = ras;
    }

    public void makan() {
        System.out.println(nama + " sedang makan.");
    }
}
