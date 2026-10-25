package Q3_Multithreading;

public class TelemetryThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            double cpu = 30.0 + (i * 4.5);
            double memory = 45.0 + (i * 2.0);

            System.out.printf(
                "[%s] Check %d | Simulated CPU: %.1f%% | "
                + "Simulated Memory: %.1f%%%n",
                Thread.currentThread().getName(),
                i, cpu, memory
            );

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}