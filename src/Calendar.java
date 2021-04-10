import java.util.HashMap;

public class Calendar {

    private HashMap<Integer, WaterUsage> usageMap;

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

}
