import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FlightSearchService {
    private static FlightSearchService FSS;
    FlightSearchDAO FSDao;

    public FlightSearchService(FlightSearchDAO FSDao) {
        this.FSDao = FSDao;
    }

    public static FlightSearchService getInstance(FlightSearchDAO FSDao) {
        if (FlightSearchService.FSS != null) {
            return FSS;
        }
        FlightSearchService.FSS = new FlightSearchService(FSDao);
        return FSS;

    }

    public List<Flight> getAllFlights() {
        return FSDao.getAllFlights();
    }

    public Flight getFlightById(String id) {
        return FSDao.getFlightById(id);
    }

    public boolean deleteFlightById(String id) throws IOException {
        return FSDao.deleteFlightById(id);
    }

    public Flight saveFlight(Flight f) {
        return FSDao.saveFlight(f);
    }

    public void saveDataToDB(List<Flight> flights) throws IOException {
        FSDao.saveDataToDB(flights);
    }

    public List<Flight> getDataFromDB() throws IOException, ClassNotFoundException {
        return FSDao.getDataFromDB();
    }

    public void makeRandomFlights(int number) {
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            flights.add(new Flight());
        }
        try {
            this.saveDataToDB(flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
