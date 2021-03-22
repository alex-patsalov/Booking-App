import java.io.IOException;
import java.util.List;

public final class FlightSearchController {
    private static FlightSearchController FSC;
    FlightSearchService FSS;

    public FlightSearchController(FlightSearchService FSS){
        this.FSS = FSS;
    }

    public static FlightSearchController getInstance(FlightSearchService FSS) {
        if (FlightSearchController.FSC != null) {
            return FSC;
        }
        FlightSearchController.FSC = new FlightSearchController(FSS);
        return FSC;

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
