package Q3_Multithreading;

public class AsyncWebEngineApp {

    public static void main(String[] args) {

        System.out.println(
            "Main Thread: " + Thread.currentThread().getName()
        );

        TelemetryThread telemetry = new TelemetryThread();
        telemetry.setName("Telemetry-Worker");

        LogProcessorTask logTask = new LogProcessorTask();

        Thread logger = new Thread(logTask, "Log-Worker");

        telemetry.start();
        logger.start();

        System.out.println("Both workers started.");
    }
}