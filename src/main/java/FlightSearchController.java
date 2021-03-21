import java.io.IOException;
import java.util.List;

public class FlightSearchController {
    public FlightSearchService FSS;

    public FlightSearchController(){
        FSS = new FlightSearchService();
    }

    public List<Flight> getAllFlights(){
        return FSS.getAllFlights();
    }

    public Flight getFlightById(String id){
        return FSS.getFlightById(id);
    }

    public boolean deleteFlightById(String id) throws IOException {
        return FSS.deleteFlightById(id);
    }

    public Flight saveFlight(Flight f){
        return FSS.saveFlight(f);
    }

    public void saveDataToDB(List<Flight> flights) throws IOException {
        FSS.saveDataToDB(flights);
    }

    public List<Flight> getDataFomDB() throws IOException, ClassNotFoundException {
        return FSS.getDataFromDB();
    }

    public void makeRandomFlights(int number){
        FSS.makeRandomFlights(number);
    }
}
