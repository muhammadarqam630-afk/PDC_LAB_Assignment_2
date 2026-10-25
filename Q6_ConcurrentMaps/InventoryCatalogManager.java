package Q6_ConcurrentMaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InventoryCatalogManager {

    private static final int THREADS = 10;
    private static final int OPERATIONS = 50000;
    private static final int PRODUCTS = 100;

    private static Map<String, Integer> createInventory(Map<String, Integer> map) {
        for (int i = 0; i < PRODUCTS; i++) {
            map.put("PRODUCT-" + i, 100);
        }
        return map;
    }

    private static double runBenchmark(
            Map<String, Integer> inventory,
            String name) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(THREADS);

        int operationsPerWorker = OPERATIONS / THREADS;

        List<Future<Long>> futures = new ArrayList<>();

        long start = System.nanoTime();

        for (int t = 0; t < THREADS; t++) {

            final int workerId = t;

            futures.add(executor.submit(() -> {

                long checksum = 0;

                for (int i = 0; i < operationsPerWorker; i++) {

                    String productId =
                        "PRODUCT-" + ((workerId + i) % PRODUCTS);

                    if (i % 2 == 0) {

                        inventory.compute(
                            productId,
                            (key, stock) -> stock + 1
                        );

                    } else {

                        checksum += inventory.get(productId);
                    }
                }

                return checksum;
            }));
        }

        long combinedChecksum = 0;

        for (Future<Long> future : futures) {
            combinedChecksum += future.get();
        }

        executor.shutdown();

        if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
            throw new IllegalStateException(
                "Benchmark workers did not terminate");
        }

        double seconds = (System.nanoTime() - start) / 1_000_000_000.0;
        double throughput = OPERATIONS / seconds;

        System.out.println("\n===== " + name + " =====");
        System.out.println("Workers: " + THREADS);
        System.out.println("Total Operations: " + OPERATIONS);
        System.out.println("Read Checksum: " + combinedChecksum);
        System.out.printf(
            "Execution Time: %.3f ms%n",
            seconds * 1000
        );
        System.out.printf(
            "Throughput: %.2f operations/second%n",
            throughput
        );

        return seconds;
    }

    public static double benchmarkSynchronizedMap() throws Exception {
        Map<String, Integer> map = createInventory(
                Collections.synchronizedMap(new HashMap<>())
        );
        return runBenchmark(map, "SYNCHRONIZED MAP");
    }

    public static double benchmarkConcurrentMap() throws Exception {
        Map<String, Integer> map = createInventory(new ConcurrentHashMap<>());
        return runBenchmark(map, "CONCURRENT HASH MAP");
    }

    public static void main(String[] args) throws Exception {
        benchmarkSynchronizedMap();
        benchmarkConcurrentMap();
    }
}