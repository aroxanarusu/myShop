package products;

public class Phone extends ProdusElectronic{

    private int numbOfSims;
    private boolean hasTouch;


    public Phone(long id, double price, String name, int numbOfSims, boolean hasTouch, int voltaj) {
        super(id, price, name, voltaj);
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
    public String toString() {
        return "Phone{" +
                "numbOfSim" + numbOfSims +
                ", hasTouchs==" + hasTouch +
                '}'+super.toString();
    }
}
