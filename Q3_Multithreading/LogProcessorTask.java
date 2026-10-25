package Q3_Multithreading;

public class LogProcessorTask implements Runnable {

    @Override
public void run() {

    for (int i = 1; i <= 5; i++) {
        System.out.println("Processing log batch: " + i);
    }
}
}