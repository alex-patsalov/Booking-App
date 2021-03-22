import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FlightSearchDAO {

    List<Flight> getAllFlights();
    Flight getFlightById(String id) throws IndexOutOfBoundsException;
    boolean deleteFlightById(String id) throws IOException;
    Flight saveFlight(Flight flight);
    void saveDataToDB(List<Flight> flight) throws IOException;
    List<Flight> getDataFromDB() throws IOException, ClassNotFoundException;

}
