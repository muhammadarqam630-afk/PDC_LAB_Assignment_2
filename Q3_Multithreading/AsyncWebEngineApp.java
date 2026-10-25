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
        System.out.println("Telemetry State: " + telemetry.getState());
System.out.println("Logger State: " + logger.getState());

try {
    telemetry.join();
    logger.join();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    System.out.println("Main thread interrupted.");
    return;
}

System.out.println("Telemetry Final State: " + telemetry.getState());
System.out.println("Logger Final State: " + logger.getState());

System.out.println("All worker tasks completed.");
    }
}