public class WaterUsage {

    private double consumption;
    public final static String[] QUESTIONS = {
            "How many times did you flush the toilet today?",
            "How many loads of laundry did you do today?",
            "Approximately how long (in minutes) did you shower for today?",
            "Approximately how long (in minutes) did you use the bathroom sink today?",
            "Approximately how long (in minutes) did you use the kitchen sink today?"
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
