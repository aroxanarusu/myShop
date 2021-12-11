package products;

public class Cartus extends Produs {

    private boolean isColored;

    public Cartus(long id, double price, String name, boolean isColored) {
        super(id, price, name);
        this.isColored=isColored;
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColored(boolean colored) {
        isColored = colored;
    }
}
