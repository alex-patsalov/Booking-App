import java.io.IOException;
import java.util.List;

public interface FlightBookingDAO {
    List<Flight> getAllFlights();
    List<Flight> getAvailableFlights(String destination, String date, int passengers) throws IOException, ClassNotFoundException;
    boolean deleteFlightByIdFromBookingList(String id) throws IOException;
    Flight saveFlightToBookingList(Flight flight);
    void saveDataToDB(List<Flight> flight) throws IOException;
    List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException;
    List<Flight> getBookingsFromDB() throws IOException, ClassNotFoundException;
}
