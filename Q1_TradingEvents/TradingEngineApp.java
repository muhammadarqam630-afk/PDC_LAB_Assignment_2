package Q1_TradingEvents;

public class TradingEngineApp {

    public static void main(String[] args) {

        long timestamp = System.currentTimeMillis();

        TradeEvent genericEvent = new TradeEvent(
                "GENERAL-001", timestamp, 10000.00);

        TradeEvent fixEvent = new FIXOrderEvent(
                "FIX-GATEWAY", timestamp, 10000.00, "ORDER-101");

        TradeEvent reutersEvent = new ReutersQuoteEvent(
                "REUTERS-GATEWAY", timestamp,
                150.00, 149.50, 150.20);

        System.out.println("===== FINANCIAL TRADING ENGINE =====");

        genericEvent.dispatch();
        fixEvent.dispatch();
        reutersEvent.dispatch();

        System.out.println("\nAll trading events processed.");
    }
}