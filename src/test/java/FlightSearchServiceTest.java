import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlightSearchServiceTest {
    static FlightsDB DB = null;
    static FlightSearchService FSS = null;
    static Flight f = new Flight();
//    static File file = null;
    @BeforeAll
    public static void beforeAll(){
        DB = new FlightsDB();
        FSS = new FlightSearchService(DB);
//        file = new File("FlightsDBforTests.bin");
    }
    @AfterAll
    public static void afterAll(){
        FSS = null;
//        file.delete();
    }
    @Test
    void getInstance() {
        FlightSearchService FSS = new FlightSearchService(DB);
        Assertions.assertEquals(FSS.FSDao, DB);
    }

    @Test
    void getAllFlights() {
        List<Flight> allFlights = FSS.getAllFlights();
        Assertions.assertEquals(10, allFlights.size());
    }

    @Test
    void getFlightById() throws IOException, ClassNotFoundException {
        List<Flight> allFlights = new ArrayList<Flight>();
        allFlights.add(f);
        String id = f.getId();
        FSS.saveFlight(f);
        Flight flightById = FSS.getFlightById(id);
        Assertions.assertEquals(flightById.getId(), id);
    }

    @Test
    void deleteFlightById() throws IOException {
        List<Flight> allFlights = new ArrayList<Flight>();
        allFlights.add(f);
        String id = "UA449";
        FSS.saveFlight(f);
        boolean bool = FSS.deleteFlightById(id);
        boolean deleted = allFlights.remove(f);
        Assertions.assertEquals(bool, deleted);
        allFlights.add(f);
    }

    @Test
    void saveFlight() {
        Flight flight = FSS.saveFlight(f);
        boolean contains = FSS.getAllFlights().contains(f);
        Assertions.assertTrue(contains);
    }

    @Test
    void saveDataToDB() {

    }

    @Test
    void getDataFromDB() {

    }

    @Test
    void makeRandomFlights() throws IOException, ClassNotFoundException {
        FSS.makeRandomFlights(10);
        Assertions.assertEquals(10, FSS.getDataFromDB().size());
    }
}