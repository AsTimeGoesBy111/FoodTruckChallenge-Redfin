import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public final class FoodTruckFinderUtil {

    /* hits the url and gathers the food truck data */
    public static String retrieveFoodTruckFromJson(String source) throws IOException {
        StringBuilder foodTruckData = new StringBuilder();
        URL url = new URL(source);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            foodTruckData.append(line);
        }
        rd.close();
        return foodTruckData.toString();
    }

    /* method that returns the day of week */
    public static int getDayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
