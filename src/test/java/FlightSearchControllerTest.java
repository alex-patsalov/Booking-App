import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightSearchControllerTest {

    static FlightsDB DB = null;
    static FlightSearchService FSS = null;
    static FlightSearchController FSC = null;
    static Flight f = new Flight();
        static File file = null;
    @BeforeAll
    public static void beforeAll(){
        DB = new FlightsDB();
        FSS = new FlightSearchService(DB);
        FSC = new FlightSearchController(FSS);
        file = new File("FlightsDBforTests.bin");
    }
    @AfterAll
    public static void afterAll(){
        FSC = null;
        file.delete();
    }

    @Test
    void getInstance() {
        FlightSearchController FSC = new FlightSearchController(FSS);
        Assertions.assertEquals(FSC.FSS, FSS);
    }

    @Test
    void getAllFlights() {
        List<Flight> allFlights = FSC.getAllFlights();
        Assertions.assertEquals(10, allFlights.size());
    }

    @Test
    void getFlightById() throws IOException, ClassNotFoundException {
        List<Flight> allFlights = new ArrayList<>();
        allFlights.add(f);
        String id = f.getId();
        FSC.saveFlight(f);
        Flight flightById = FSC.getFlightById(id);
        Assertions.assertEquals(flightById.getId(), id);
    }

    @Test
    void deleteFlightById() throws IOException {
        List<Flight> allFlights = new ArrayList<>();
        allFlights.add(f);
        String id = "UA449";
        FSS.saveFlight(f);
        boolean bool = FSC.deleteFlightById(id);
        boolean deleted = allFlights.remove(f);
        Assertions.assertEquals(bool, deleted);
        allFlights.add(f);
    }

    @Test
    void saveFlight() {
        Flight flight = FSC.saveFlight(f);
        boolean contains = FSC.getAllFlights().contains(f);
        Assertions.assertTrue(contains);
    }

    @Test
    void saveDataToDB() throws IOException, ClassNotFoundException {
        List<Flight> flights = new ArrayList<>();
        flights.add(f);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(flights);
        oos.close();
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Flight> allFLights = (List<Flight>)ois.readObject();
        ois.close();
        Assertions.assertEquals(flights,allFLights);
    }

    @Test
    void getDataFromDB() throws IOException, ClassNotFoundException {
        List<Flight> flights = new ArrayList<>();
        flights.add(f);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(flights);
        oos.close();
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Flight> allFLights = (List<Flight>)ois.readObject();
        ois.close();
        Assertions.assertEquals(flights,allFLights);
    }

    @Test
    void getInfoAboutFlight() {
        String info = f.getPrettyFormat();
        String id = info.substring(1, 7);
        String flightCompany = id.substring(0, 2);
        Assertions.assertEquals(2, flightCompany.length());
    }

    @Test
    void makeRandomFlights() throws IOException, ClassNotFoundException {
        FSC.makeRandomFlights(10);
        Assertions.assertEquals(10, FSC.getDataFromDB().size());
    }
}