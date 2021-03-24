import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightSearchServiceTest {
    static FlightsDB DB = new FlightsDB();
    static FlightSearchService FSS = null;
//    static Flight f = new Flight();
//    static File file = null;
    @BeforeEach
    public void before(){
        FSS = new FlightSearchService(DB);
//        file = new File("FlightsDBforTests.bin");
    }
    @AfterEach
    public void after(){
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
        List<Flight> expected = new ArrayList<>();
        Assertions.assertEquals(expected, allFlights);
    }

    @Test
    void getFlightById() {
    }

    @Test
    void deleteFlightById() {
    }

    @Test
    void saveFlight() {
    }

    @Test
    void saveDataToDB() {
    }

    @Test
    void getDataFromDB() {
    }

    @Test
    void makeRandomFlights() {
    }
}