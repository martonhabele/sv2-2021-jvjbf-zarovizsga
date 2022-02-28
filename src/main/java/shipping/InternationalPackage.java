package shipping;

public class InternationalPackage implements Transportable {
    public static final int BASE_PRICE = 1_200;
    public static final int BREAKABLE_MULTIPLIER = 2;
    public static final int PRICE_PER_KM = 10;

    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        if (breakable) {
            return BASE_PRICE * BREAKABLE_MULTIPLIER + distance * PRICE_PER_KM;
        } else {
            return BASE_PRICE + distance * PRICE_PER_KM;
        }
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }
}