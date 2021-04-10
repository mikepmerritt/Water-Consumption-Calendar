import java.util.HashMap;

public class Calendar {

    private HashMap<Integer, WaterUsage> usageMap;
    private int startDay, endDate;
    private String name;

    public Calendar(int startDay, int endDate, String name) {
        usageMap = new HashMap<Integer, WaterUsage>();
        this.startDay = startDay % 7;
        this.endDate = endDate;
        this.name = name;
    }

    public WaterUsage getDay(int day) {
        return usageMap.get(day);
    }

    public boolean addDay(int day, WaterUsage usage) {
        if (usageMap.get(day) == null) {
            usageMap.put(day, usage);
            return true; // sends a true if the data could be added to that day
        }
        else {
            return false; // sends false if data already exists, call overwrite
        }

    }

    public void overwriteDay(int day, WaterUsage usage) {
        usageMap.replace(day, usage);
    }

    // some debug stuff, dumps contents of hash map
    public void outputAll() {
        System.out.println(usageMap.size());
        for (int i = 1; i <= endDate; i++) {
            System.out.println("key: " + i + "\tvalue: " + usageMap.get(i).getConsumption());
        }
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

}
