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

    public static void main(String[] args) {

        Map<String, Integer> inventory =
                createInventory(new HashMap<>());

        System.out.println("Products: " + inventory.size());
        System.out.println("Workers required: " + THREADS);
        System.out.println("Operations required: " + OPERATIONS);
    }
}