import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FlightsDBTest {
    static FlightsDB DB = null;
    @BeforeEach
    public void before(){
        DB = new FlightsDB(){{new Flight();}};
    }
    @AfterEach
    public void after(){
        DB = null;
    }

    @Test
    void getAllFlights() {
        List<Flight> allFlights = new ArrayList<Flight>();
        Assertions.assertEquals(DB.getAllFlights(), allFlights);
    }

    @Test
    void getFlightById() {
        List<Flight> allFlights = new ArrayList<Flight>();
        Flight f = new Flight();
        allFlights.add(f);
        String id = "DE490";
        DB.saveFlight(f);
        Optional<Flight> flightById = DB.getFlightById(id);
        Flight flight = flightById.get();
        Assertions.assertEquals(flight.getId(), id);
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
}