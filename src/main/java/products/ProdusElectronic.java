package products;

public abstract class ProdusElectronic extends Produs {

    private int voltaj;

    public ProdusElectronic(long id, double price, String name, int voltaj) {
        super(id, price, name);
        this.voltaj=voltaj;
    }

    public int getVoltaj() {
        return voltaj;
    }

    public void setVoltaj(int voltaj) {
        this.voltaj = voltaj;
    }

    @Override
    public String toString() {
        return "ProdusElectronic{" +
                "voltaj=" + voltaj +
                '}'+ super.toString();
    }
}
