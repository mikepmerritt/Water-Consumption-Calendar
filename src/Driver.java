
public class Driver {

	public static void main(String[] args) {
		System.out.println("April");
		Calendar april = new Calendar(4, 30, "Water Consumption - April");

		// these loops can be reused to fill in a grid for the month view
		for (int i = 0; i < april.getStartDay(); i++) {
			System.out.print(" \t");
		}
		for (int i = 1; i <= april.getEndDate(); i++) {
			System.out.print(i+"\t");

			if ((i + april.getStartDay()) % 7 == 0) {
				System.out.println(); // wrapping to next line
			}
		}

		new MonthWindow(april);

	}

}
