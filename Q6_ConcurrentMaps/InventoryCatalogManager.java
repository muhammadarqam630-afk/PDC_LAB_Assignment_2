package Q6_ConcurrentMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class InventoryCatalogManager {

    private static final int THREADS = 10;
    private static final int OPERATIONS = 50000;
    private static final int PRODUCTS = 100;

    private static Map<String, Integer> createInventory(
            Map<String, Integer> map) {

        for (int i = 0; i < PRODUCTS; i++) {
            map.put("PRODUCT-" + i, 100);
        }

        return map;
    }
    private static double runBenchmark(
        Map<String, Integer> inventory,
        String name) throws Exception {

    long start = System.nanoTime();

    long total = 0;

    for (int i = 0; i < OPERATIONS; i++) {
        String product = "PRODUCT-" + (i % PRODUCTS);

        inventory.compute(product, (key, stock) -> stock + 1);
        total += inventory.get(product);
    }

    double seconds =
            (System.nanoTime() - start) / 1_000_000_000.0;

    System.out.println(name);
    System.out.println("Read checksum: " + total);
    System.out.printf("Execution Time: %.3f ms%n", seconds * 1000);

    return seconds;
}
public static double benchmarkSynchronizedMap()
        throws Exception {

    Map<String, Integer> map = createInventory(
        Collections.synchronizedMap(new HashMap<>())
    );

    return runBenchmark(map, "SYNCHRONIZED MAP");
}
    public static void main(String[] args) throws Exception {
    benchmarkSynchronizedMap();
}
}