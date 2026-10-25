package Q1_TradingEvents;

public class FIXOrderEvent extends TradeEvent {

    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp,
                         double basePrice, String clOrdId) {

        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {

        double fee = basePrice * 0.0015;
        double total = basePrice + fee;

        System.out.println("\n===== FIX STOCK PURCHASE =====");
        System.out.println("Order ID: " + clOrdId);
        System.out.println("Sender ID: " + senderId);
        System.out.println("Timestamp: " + timestamp);
        System.out.printf("Base Price: %.2f%n", basePrice);
        System.out.printf("Transaction Fee (0.15%%): %.2f%n", fee);
        System.out.printf("Total Purchase Price: %.2f%n", total);
    }
}