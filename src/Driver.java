
public class Driver {

	public static void main(String[] args) {
		Calendar april = new Calendar(4, 30, "Water Consumption - April");

		MonthWindow monthWindow = new MonthWindow(april);

//		for (int i = 1; i <= april.getEndDate(); i++) {
//			april.addDay(i, new WaterUsage(new double[] {i, 0, 0, 0, 0}));
//		}

	}

}
