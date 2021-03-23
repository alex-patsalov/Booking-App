import java.io.IOException;
import java.util.List;

public class FlightBookingService {
    private static FlightBookingService INSTANCE;
    public FlightBookingDAO FBDao;

    FlightBookingService(FlightBookingDAO flightBooking) {
        this.FBDao =  flightBooking;
    }

    public static FlightBookingService getInstance(FlightBookingDAO flightBooking) {
        if (FlightBookingService.INSTANCE != null) {
            return INSTANCE;
        }
        FlightBookingService.INSTANCE = new FlightBookingService(flightBooking);
        return INSTANCE;
    }

    public List<Flight> getAvailableFlights(String destination, String date, int passengers) throws IOException, ClassNotFoundException {
        return FBDao.getAvailableFlights(destination,date,passengers);
    }

    public List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException {
        return FBDao.getFlightsFromDB();
    }
}
