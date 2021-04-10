public class WaterUsage {

    private double consumption;
    private final static String[] questions = {

    }; // fill in with question strings later
    private double[] inputs;

    public WaterUsage(double[] inputs) {
        consumption = 0;
        this.inputs = inputs;
        for (double value : inputs) {
            consumption += value;
        }
    }

    public double getConsumption() {
        return consumption;
    }

}
