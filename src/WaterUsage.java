public class WaterUsage {

    private double consumption;
    public final static String[] QUESTIONS = {
            "How many times did you flush the toilet today?",
            "How many loads of laundry did you do today?",
            "Approximately how long (in minutes) did you shower for today?",
            "Approximately how long (in minutes) did you use the bathroom sink today?",
            "Approximately how long (in minutes) did you use the kitchen sink today?"
    };
    public final static double[] DEFAULT_MULTIPLIERS = {
    		1.6,
    		14.0,
    		2.1,
    		1.5,
    		2.2
    };
    private double[] inputs;

    public WaterUsage(double[] inputs) {
        consumption = 0;
        this.inputs = inputs;
        for (int i = 0; i < inputs.length; i++) {
        	consumption += inputs[i] * DEFAULT_MULTIPLIERS[i];
        }
    }

    public double getConsumption() {
        return consumption;
    }

    public double getAnswer(int question) {
        return inputs[question];
    }

}
