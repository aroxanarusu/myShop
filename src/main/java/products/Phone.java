package products;

public class Phone extends ProdusElectronic{

    private int numbOfSims;
    private boolean hasTouch;


    public Phone(long id, double price, String name, int numbOfSims, boolean hasTouch, int voltage) {
        super(id, price, name, voltage);
        this.numbOfSims=numbOfSims;
        this.hasTouch=hasTouch;
    }

    public int getNumbOfSims() {
        return numbOfSims;
    }

    public void setNumbOfSims(int numbOfSims) {
        this.numbOfSims = numbOfSims;
    }

    public boolean isHasTouch() {
        return hasTouch;
    }

    public void setHasTouch(boolean hasTouch) {
        this.hasTouch = hasTouch;
    }

    @Override
    public String prettyPrint() {
        return super.prettyPrint() +
                "\nNumber of sims: " + this.numbOfSims +
                "\nHas touch?: " + this.hasTouch;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "numbOfSims=" + numbOfSims +
                ", hasTouch=" + hasTouch +
                "} " + super.toString();
    }
}
