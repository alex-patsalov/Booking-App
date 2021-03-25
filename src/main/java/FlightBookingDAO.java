import java.io.IOException;
import java.util.List;

public interface FlightBookingDAO {
    List<Flight> getAllFlights();
    List<Flight> getAvailableFlights(String destination, String date, int passengers) throws IOException, ClassNotFoundException;
    boolean deleteFlightByIdFromBookingList(String id) throws IOException;
    Booking saveFlightToBookingList(Flight flight) throws IOException, ClassNotFoundException;
    void saveBookingsToDB(List<Booking> bookings) throws IOException;
    List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException;
    List<Booking> getBookingsFromDB() throws IOException, ClassNotFoundException;
}
