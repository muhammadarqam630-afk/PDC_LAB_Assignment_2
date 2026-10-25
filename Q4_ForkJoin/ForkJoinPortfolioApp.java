package Q4_ForkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    private static double sequentialSum(double[] assets) {
        double sum = 0;

        for (double asset : assets) {
            sum += asset;
        }

        return sum;
    }

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
        double serialRisk = sequentialSum(assets);
        double difference = Math.abs(serialRisk - originalRisk);

        if (difference > 0.0001) {
            throw new AssertionError("Serial and parallel sums differ");
        }

        for (int i = 0; i < 3; i++) {
            pool.invoke(new ParallelRiskCalculator(assets, 0, size));
        }

        long serialStart = System.nanoTime();
        serialRisk = sequentialSum(assets);
        long serialEnd = System.nanoTime();

        long parallelStart = System.nanoTime();
        originalRisk = pool.invoke(new ParallelRiskCalculator(assets, 0, size));
        long parallelEnd = System.nanoTime();

        double serialMs = (serialEnd - serialStart) / 1_000_000.0;
        double parallelMs = (parallelEnd - parallelStart) / 1_000_000.0;

        pool.invoke(new PortfolioThresholdTask(assets, 0, size, 1500.0));
        double adjustedRisk = pool.invoke(new ParallelRiskCalculator(assets, 0, size));

        System.out.println("===== PORTFOLIO ASSESSMENT =====");
        System.out.println("Number of Assets: " + size);
        System.out.printf("Original Risk: %.2f%n", originalRisk);
        System.out.printf("Adjusted Risk: %.2f%n", adjustedRisk);
        System.out.printf("Sequential Risk: %.2f%n", serialRisk);
        System.out.printf("Risk Difference: %.6f%n", difference);
        System.out.printf("Sequential Time: %.4f ms%n", serialMs);
        System.out.printf("Parallel Time: %.4f ms%n", parallelMs);

        if (parallelMs > 0) {
            System.out.printf("Parallel Speedup: %.2fx%n", serialMs / parallelMs);
        }

        pool.shutdown();
    }
}