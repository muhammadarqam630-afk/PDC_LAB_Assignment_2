package Q5_SensorSearch;

public class ParallelMinSearch extends SensorSearchEngine {

    public ParallelMinSearch(double[] data, int start, int end) {
        super(data, start, end);
    }

    @Override
    public void processGrid() {

        double minimum = Double.POSITIVE_INFINITY;

        for (int i = startIdx; i < endIdx; i++) {
            minimum = Math.min(minimum, sensorData[i]);
        }

        result = minimum;
    }
}