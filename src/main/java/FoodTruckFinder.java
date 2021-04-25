import org.json.*;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.*;
import java.text.*;
import java.time.*;
import org.json.JSONArray;

public class FoodTruckFinder {
    private final String foodTruckData;
    private final Date date;
    private final List<FoodTruck> foodTruckResults = new ArrayList<>();

    /* constructor that takes a source and date */
    public FoodTruckFinder(String source, Date date) throws IOException {
        this.foodTruckData = FoodTruckFinderUtil.retrieveFoodTruckFromJson(source);
        this.date = date;
    }

    /* method to filter and sort food trucks */
    public void filterAndSortFoodTrucksByDate() throws ParseException {
        JSONArray array = new JSONArray(foodTruckData.toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String searchTime=dateFormat.format(date);
        for (int i = 0; i < array.length(); i++) {
            JSONObject foodTruck = array.getJSONObject(i);
            if (Integer.valueOf((String) foodTruck.get("dayorder")) == FoodTruckFinderUtil.getDayOfWeek(date)) {
                if (searchTime.compareTo((String) foodTruck.get("start24"))>0 && ((String) foodTruck.get("end24")).compareTo(searchTime)>0) {
                    foodTruckResults.add(new FoodTruck((String) foodTruck.get("applicant"), (String) foodTruck.get("location")));
                }
            }
        }
        Collections.sort(foodTruckResults, (a, b) -> a.getName().compareTo(b.getName()));
    }



    public void printList(int pageSize, int startIndex) {
        int i = 0;
        while (startIndex < foodTruckResults.size() && i < pageSize) {
            System.out.println(foodTruckResults.get(startIndex).getName() + " - " + foodTruckResults.get(startIndex).getLocation());
            i++;
            startIndex++;
        }

        if (startIndex >= foodTruckResults.size()) {
            System.out.println("No remaining results to print");
            return;
        }

        System.out.println("Print the remaining pages? Y/N");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("Y")) {
            printList( pageSize, startIndex);
        }
    }

    /* main method that takes care of the program core logic */
    public static void main(String[] args) {
        try {
            System.out.println("Do you want to view food trucks open now?");
            Scanner myObj = new Scanner(System.in);
            FoodTruckFinder foodTruckFinder=new FoodTruckFinder("https://data.sfgov.org/resource/bbb8-hzi6.json",Date.from(Instant.now()));
            foodTruckFinder.filterAndSortFoodTrucksByDate();
            foodTruckFinder.printList(10,0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

