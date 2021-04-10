public class WaterUsage {

    private double consumption;
    public final static String[] QUESTIONS = {
            "Question 1:",
            "Question 2:",
            "Question 3:"
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

    public double getAnswer(int question) {
        return inputs[question];
    }

}
