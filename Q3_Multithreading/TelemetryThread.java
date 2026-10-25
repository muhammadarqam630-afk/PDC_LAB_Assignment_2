package Q3_Multithreading;

public class TelemetryThread extends Thread {

    @Override
    public void run() {
        System.out.println("Telemetry worker initialized.");
    }
}