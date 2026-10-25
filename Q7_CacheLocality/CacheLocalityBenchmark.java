package Q7_CacheLocality;

public class CacheLocalityBenchmark {

    private static final int N = 2000;

    private static final double[][] matrix =
            new double[N][N];

    private static volatile double resultSink;

    public static void initializeMatrix() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = i + j * 0.5;
            }
        }
    }
public static double traverseRowMajor() {

    double sum = 0;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            sum += matrix[i][j];
        }
    }

    return sum;
}

public static double traverseColumnMajor() {

    double sum = 0;

    for (int j = 0; j < N; j++) {
        for (int i = 0; i < N; i++) {
            sum += matrix[i][j];
        }
    }

    return sum;
}

   public static void main(String[] args) {

    initializeMatrix();

    System.out.println("Warming up JVM...");

    for (int i = 0; i < 5; i++) {
        resultSink = traverseRowMajor();
        resultSink = traverseColumnMajor();
    }

    final int RUNS = 10;

    double totalRowTime = 0;
    double totalColumnTime = 0;

    System.out.println("===== CACHE LOCALITY BENCHMARK =====");
    System.out.println("Matrix Size: 2000 x 2000");

    for (int run = 1; run <= RUNS; run++) {

        long rowStart = System.nanoTime();
        double rowSum = traverseRowMajor();
        long rowEnd = System.nanoTime();

        long columnStart = System.nanoTime();
        double columnSum = traverseColumnMajor();
        long columnEnd = System.nanoTime();

        if (Math.abs(rowSum - columnSum) > 0.01) {
            throw new AssertionError("Traversal sums differ");
        }

        double rowMs =
                (rowEnd - rowStart) / 1_000_000.0;

        double columnMs =
                (columnEnd - columnStart) / 1_000_000.0;

        totalRowTime += rowMs;
        totalColumnTime += columnMs;

        System.out.printf(
            "Run %d | Row: %.3f ms | Column: %.3f ms%n",
            run, rowMs, columnMs
        );
    }

    double averageRow = totalRowTime / RUNS;
    double averageColumn = totalColumnTime / RUNS;

    System.out.println("\n===== FINAL RESULTS =====");

    System.out.printf(
        "Average Row-Major Time: %.3f ms%n",
        averageRow
    );

    System.out.printf(
        "Average Column-Major Time: %.3f ms%n",
        averageColumn
    );

    System.out.printf(
        "Row-Major Speedup: %.2fx%n",
        averageColumn / averageRow
    );
}
}