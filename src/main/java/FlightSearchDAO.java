import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FlightSearchDAO {

    List<Flight> getAllFlights();
    Flight getFlightById(String id) throws IOException, ClassNotFoundException;
    boolean deleteFlightById(String id) throws IOException;
    Flight saveFlight(Flight flight);
    void saveDataToDB(List<Flight> flight) throws IOException;
    List<Flight> getDataFromDB() throws IOException, ClassNotFoundException;

}
