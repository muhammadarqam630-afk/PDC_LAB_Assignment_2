package Q1_TradingEvents;

public class TradingEngineTest {

    public static void main(String[] args) {

        double basePrice = 10000.00;
        double fee = basePrice * 0.0015;
        double spread = 150.20 - 149.50;

        if (Math.abs(fee - 15.0) > 0.000001) {
            throw new AssertionError("Incorrect transaction fee");
        }

        if (Math.abs(spread - 0.70) > 0.000001) {
            throw new AssertionError("Incorrect market spread");
        }

        System.out.println("Q1 calculation checks passed.");
    }
}