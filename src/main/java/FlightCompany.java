import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum FlightCompany {
    UA, FS, CZ, DE, FR, ES;

    public static List<FlightCompany> flightCompanies = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random random = new Random();

    public static FlightCompany getRandomCompany()  {
        return flightCompanies.get(random.nextInt(flightCompanies.size()));
    }

    public static String generateRandomId(){
        int number = random.nextInt(900) + 100;
        return getRandomCompany().toString() + number;
    }

}
