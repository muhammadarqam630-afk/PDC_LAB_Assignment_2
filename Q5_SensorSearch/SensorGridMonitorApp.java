package Q5_SensorSearch;

import java.util.Random;

public class SensorGridMonitorApp {

    public static void main(String[] args) {

        final int SIZE = 50000;
        double[] sensorData = new double[SIZE];

        Random random = new Random(42);

        for (int i = 0; i < SIZE; i++) {
            sensorData[i] = -20 + random.nextDouble() * 140;
        }

        int middle = SIZE / 2;

        System.out.println("===== SENSOR GRID MONITOR =====");
        System.out.println("Total Sensors: " + SIZE);
        System.out.println("Four worker threads will be used.");
        System.out.println("Partition 1: 0 to " + middle);
        System.out.println("Partition 2: " + middle + " to " + SIZE);
    }
}