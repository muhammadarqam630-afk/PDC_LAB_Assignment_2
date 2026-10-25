package Q5_SensorSearch;

public abstract class SensorSearchEngine implements Runnable {

    protected final double[] sensorData;
    protected final int startIdx;
    protected final int endIdx;

    protected double result;

    public SensorSearchEngine(
            double[] sensorData, int startIdx, int endIdx) {

        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public abstract void processGrid();

    @Override
    public void run() {
        processGrid();
    }

    public double getResult() {
        return result;
    }
}