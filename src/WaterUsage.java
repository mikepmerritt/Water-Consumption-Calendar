public class WaterUsage {

    private double consumption;
    private int[] inputs;

    public WaterUsage(int[] inputs) {
        consumption = 0;
        this.inputs = inputs;
        for (int value : inputs) {
            consumption += value;
        }
    }

    public double getConsumption() {
        return consumption;
    }

}
