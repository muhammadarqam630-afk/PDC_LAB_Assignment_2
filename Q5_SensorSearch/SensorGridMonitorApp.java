package Q5_SensorSearch;

import java.util.Random;

public class SensorGridMonitorApp {

    public static void main(String[] args)
        throws InterruptedException {

        final int SIZE = 50000;
        double[] sensorData = new double[SIZE];

        Random random = new Random(42);

        for (int i = 0; i < SIZE; i++) {
            sensorData[i] = -20 + random.nextDouble() * 140;
        }

        int middle = SIZE / 2;
ParallelMinSearch minLeft =
        new ParallelMinSearch(sensorData, 0, middle);

ParallelMinSearch minRight =
        new ParallelMinSearch(sensorData, middle, SIZE);

ParallelMaxSearch maxLeft =
        new ParallelMaxSearch(sensorData, 0, middle);

ParallelMaxSearch maxRight =
        new ParallelMaxSearch(sensorData, middle, SIZE);

Thread t1 = new Thread(minLeft, "Min-Worker-1");
Thread t2 = new Thread(minRight, "Min-Worker-2");
Thread t3 = new Thread(maxLeft, "Max-Worker-1");
Thread t4 = new Thread(maxRight, "Max-Worker-2");
t1.join();
t2.join();
t3.join();
t4.join();

System.out.println("All sensor workers completed.");
t1.start();
t2.start();
t3.start();
t4.start();
        System.out.println("===== SENSOR GRID MONITOR =====");
        System.out.println("Total Sensors: " + SIZE);
        System.out.println("Four worker threads will be used.");
        System.out.println("Partition 1: 0 to " + middle);
        System.out.println("Partition 2: " + middle + " to " + SIZE);
    }
}