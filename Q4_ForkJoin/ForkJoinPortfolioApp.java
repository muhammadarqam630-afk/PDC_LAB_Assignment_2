package Q4_ForkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {

    public static void main(String[] args) {

        int size = 10000;
        double[] assets = new double[size];

        Random random = new Random(42);

        for (int i = 0; i < size; i++) {
            assets[i] = 500.0 + random.nextDouble() * 2000.0;
        }

        ForkJoinPool pool = new ForkJoinPool();

        double originalRisk = pool.invoke(
            new ParallelRiskCalculator(assets, 0, size));

        pool.invoke(
            new PortfolioThresholdTask(
                assets, 0, size, 1500.0));

        double adjustedRisk = pool.invoke(
            new ParallelRiskCalculator(assets, 0, size));

        System.out.println("===== PORTFOLIO ASSESSMENT =====");
        System.out.println("Number of Assets: " + size);
        System.out.printf("Original Risk: %.2f%n", originalRisk);
        System.out.printf("Adjusted Risk: %.2f%n", adjustedRisk);

        pool.shutdown();
    }
}