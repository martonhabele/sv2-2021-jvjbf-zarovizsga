package shipping;

public class NationalPackage implements Transportable {
    public static final int IN_BORDER_PRICE = 1_000;
    public static final int BREAKABLE_MULTIPLIER = 2;

    private int weight;
    private boolean breakable;

    public NationalPackage(int weight, boolean breakable) {
        this.weight = weight;
        this.breakable = breakable;
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
            return IN_BORDER_PRICE * BREAKABLE_MULTIPLIER;
        } else {
            return IN_BORDER_PRICE;
        }
    }
}