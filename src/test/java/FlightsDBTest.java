import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FlightsDBTest {
    static FlightsDB DB = null;
    static Flight f = new Flight();
    static File file = null;
//    static File file = new File("FlightsDBforTests.bin");
    @BeforeAll
    public static void beforeAll(){
        DB = new FlightsDB();
        file = new File("FlightsDBforTests.bin");
    }
    @AfterAll
    public static void afterAll(){
        DB = null;
        file.delete();
    }

    @Test
    void getAllFlights() {
        List<Flight> allFlights = new ArrayList<Flight>();
        Assertions.assertEquals(DB.getAllFlights(), allFlights);
    }

    @Test
    void getFlightById() throws IOException, ClassNotFoundException {
        List<Flight> allFlights = new ArrayList<Flight>();
        allFlights.add(f);
        String id = f.getId();
        DB.saveFlight(f);
        Flight flightById = DB.getFlightById(id);
        Assertions.assertEquals(flightById.getId(), id);
    }

    @Test
    void deleteFlightById() throws IOException {
        List<Flight> allFlights = new ArrayList<Flight>();
        allFlights.add(f);
        String id = "FR325";
        DB.saveFlight(f);
        boolean bool = DB.deleteFlightById(id);
        boolean deleted = allFlights.remove(f);
        Assertions.assertEquals(bool, deleted);
    }

    @Test
    void saveFlight() {
        Flight flight = DB.saveFlight(f);
        boolean contains = DB.getAllFlights().contains(f);
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
}