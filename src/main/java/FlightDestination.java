import java.text.SimpleDateFormat;
import java.util.*;

public enum FlightDestination {
    Berlin, Washington, Paris, Madrid, Milan, Rome, Warsaw, Odessa, Bratislava, Lublin;

    public  static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static List<FlightDestination> flightDestinations = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random random = new Random();

    public static String getRandomDestination()  {
        return flightDestinations.get(random.nextInt(flightDestinations.size())).toString();
    }

    public static String getRandomTimeOfDeparture(){
        long time = (new Date()).getTime();
        long randomTime = random.nextInt(86400000) + time;
        return format.format(randomTime);
    }
}
