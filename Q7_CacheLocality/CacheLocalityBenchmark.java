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
        double rowSum = traverseRowMajor();
        double columnSum = traverseColumnMajor();

        System.out.printf("Row-Major Sum: %.2f%n", rowSum);
        System.out.printf(
            "Column-Major Sum: %.2f%n", columnSum);
        System.out.println("Matrix Initialized: " + N + " x " + N);
    }
}