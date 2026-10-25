package Q7_CacheLocality;

public class CacheLocalityBenchmark {

    private static final int N = 2000;

    private static final double[][] matrix =
            new double[N][N];

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

    long rowStart = System.nanoTime();
    double rowSum = traverseRowMajor();
    long rowEnd = System.nanoTime();

    long columnStart = System.nanoTime();
    double columnSum = traverseColumnMajor();
    long columnEnd = System.nanoTime();

    double rowMs =
            (rowEnd - rowStart) / 1_000_000.0;

    double columnMs =
            (columnEnd - columnStart) / 1_000_000.0;

    System.out.println("===== CACHE LOCALITY BENCHMARK =====");

    System.out.printf("Row Sum: %.2f%n", rowSum);
    System.out.printf("Column Sum: %.2f%n", columnSum);

    System.out.printf("Row Time: %.3f ms%n", rowMs);
    System.out.printf("Column Time: %.3f ms%n", columnMs);
}
}