import java.io.IOException;
import java.util.List;

public class FlightSearchService {

    public FlightSearchDAO FSDao;

    public FlightSearchService(){
        FSDao = new FlightsDB();
    }

    public List<Flight> getAllFlights(){
        return FSDao.getAllFlights();
    }

    public Flight getFlightById(String id){
        return FSDao.getFlightById(id);
    }

    public boolean deleteFlightById(String id) throws IOException {
        return FSDao.deleteFlightById(id);
    }

    public Flight saveFlight(Flight f){
        return FSDao.saveFlight(f);
    }

    public void saveDataToDB(List<Flight> flights) throws IOException {
        FSDao.saveDataToDB(flights);
    }

    public List<Flight> getDataFromDB() throws IOException, ClassNotFoundException {
        return FSDao.getDataFromDB();
    }
}
