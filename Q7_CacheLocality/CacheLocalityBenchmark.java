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

    public static void main(String[] args) {

        initializeMatrix();

        System.out.println("Matrix Initialized: " + N + " x " + N);
    }
}