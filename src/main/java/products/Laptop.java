package products;

public class Laptop extends ProdusElectronic {

    private boolean hasGraphicCard;
    private int numbOfPorts;


    public Laptop(long id, double price, String name, boolean hasGraphicCard, int numbOfPorts, int voltage) {
        super(id, price, name, voltage);
        this.hasGraphicCard = hasGraphicCard;
        this.numbOfPorts = numbOfPorts;
    }

    public boolean isHasGraphicCard() {
        return hasGraphicCard;
    }

    public void setHasGraphicCard(boolean hasGraphicCard) {
        this.hasGraphicCard = hasGraphicCard;
    }

    public int getNumbOfPorts() {
        return numbOfPorts;
    }

    public void setNumbOfPorts(int numbOfPorts) {
        this.numbOfPorts = numbOfPorts;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "hasGraphicCard=" + hasGraphicCard +
                ", numbOfPorts=" + numbOfPorts +
                "} " + super.toString();
    }

    @Override
    public String prettyPrint() {
        return "Laptop\n" +
                super.prettyPrint()+
                "\nAre placa grafica? " + hasGraphicCard +
                "\nNumarul de porturi: " + numbOfPorts;
    }
}
