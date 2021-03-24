import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FlightsDBTest {
    static FlightsDB DB = null;
    static Flight f = new Flight();
    static File file = null;
    @BeforeEach
    public void before(){
        DB = new FlightsDB();
        file = new File("FlightsDBforTests.bin");
    }
    @AfterEach
    public void after(){
        DB = null;
        file.delete();
    }

    @Test
    void getAllFlights() {
        List<Flight> allFlights = new ArrayList<Flight>();
        Assertions.assertEquals(DB.getAllFlights(), allFlights);
    }

    @Test
    void getFlightById() {
        List<Flight> allFlights = new ArrayList<Flight>();
        allFlights.add(f);
        String id = "DE893";
        DB.saveFlight(f);
        Optional<Flight> flightById = DB.getFlightById(id);
        Flight flight = flightById.get();
        Assertions.assertEquals(flight.getId(), id);
    }

    @Test
    void deleteFlightById() throws IOException {
        List<Flight> allFlights = new ArrayList<Flight>();
        allFlights.add(f);
        String id = "DE490";
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