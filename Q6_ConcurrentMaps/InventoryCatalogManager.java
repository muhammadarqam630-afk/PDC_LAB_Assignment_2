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

    private static double runBenchmark(Map<String, Integer> inventory, String name,
            boolean synchronizeAccess) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Future<Long>> futures = new ArrayList<>();
        long start = System.nanoTime();

        for (int thread = 0; thread < THREADS; thread++) {
            final int threadId = thread;
            futures.add(executor.submit(() -> {
                long localTotal = 0;

                for (int i = 0; i < OPERATIONS; i++) {
                    String product = "PRODUCT-" + ((i + threadId) % PRODUCTS);

                    if (synchronizeAccess) {
                        synchronized (inventory) {
                            inventory.compute(product, (key, stock) -> stock + 1);
                            localTotal += inventory.get(product);
                        }
                    } else {
                        inventory.compute(product, (key, stock) -> stock + 1);
                        localTotal += inventory.get(product);
                    }
                }

                return localTotal;
            }));
        }

        long total = 0;
        for (Future<Long> each : futures) {
            total += each.get();
        }

        double seconds = (System.nanoTime() - start) / 1_000_000_000.0;

        executor.shutdown();

        System.out.println(name);
        System.out.println("Read checksum: " + total);
        System.out.printf("Execution Time: %.3f ms%n%n", seconds * 1000);

        return seconds;
    }

    public static double benchmarkSynchronizedMap() throws Exception {
        Map<String, Integer> map = createInventory(
                Collections.synchronizedMap(new HashMap<>())
        );
        return runBenchmark(map, "SYNCHRONIZED MAP", true);
    }

    public static double benchmarkConcurrentHashMap() throws Exception {
        Map<String, Integer> map = createInventory(new ConcurrentHashMap<>());
        return runBenchmark(map, "CONCURRENT HASH MAP", false);
    }

    public static void main(String[] args) throws Exception {
        benchmarkSynchronizedMap();
        benchmarkConcurrentHashMap();
    }
}