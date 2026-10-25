package Q5_SensorSearch;

public class ParallelMaxSearch extends SensorSearchEngine {

    public ParallelMaxSearch(double[] data, int start, int end) {
        super(data, start, end);
    }

    @Override
    public void processGrid() {

        double maximum = Double.NEGATIVE_INFINITY;

        for (int i = startIdx; i < endIdx; i++) {
            maximum = Math.max(maximum, sensorData[i]);
        }

        result = maximum;
    }
}