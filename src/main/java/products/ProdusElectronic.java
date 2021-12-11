package products;

public abstract class ProdusElectronic extends Produs {

    private int voltage;

    public ProdusElectronic(long id, double price, String name, int voltage) {
        super(id, price, name);
        this.voltage = voltage;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "ProdusElectronic{" +
                "voltage=" + voltage +
                "} " + super.toString();
    }

    @Override
    public String prettyPrint() {
        return super.prettyPrint() +
                "\nVoltage: " + this.voltage;
    }
}
