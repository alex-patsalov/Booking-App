import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightBookings implements FlightBookingDAO {
    List<Flight> allFlights = new ArrayList<>();
    @Override
    public List<Flight> getAllFlights() {
        return this.allFlights;
    }

    @Override
    public List<Flight> getAvailableFlights(String destination, String date, int passengers) throws IOException, ClassNotFoundException {
        this.getFlightsFromDB();
        System.out.println("Options for booking:\n");
        List<Flight> flights =   this.allFlights.stream().filter(fl -> (destination.equals(fl.getCityOfDestination())
                &&(fl.getTimeOfDeparture().substring(0, 10)).equals(date)) && (passengers < fl.getNumberOfFreeSeats()))
        .collect(Collectors.toList());

        return flights;
    }

    @Override
    public boolean deleteFlightByIdFromBookingList(String id) throws IOException {
        return false;
    }

    @Override
    public Flight saveFlightToBookingList(Flight flight) {
        return null;
    }

    @Override
    public void saveDataToDB(List<Flight> flight) throws IOException {

    }

    @Override
    public List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException {
        File file = new File("FlightsDB.bin");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Flight> allFlightsFromDB = (List<Flight>) ois.readObject();
        allFlights.forEach(System.out::println);
        this.allFlights = allFlightsFromDB;
        ois.close();
        return allFlightsFromDB;
    }

    @Override
    public List<Flight> getBookingsFromDB() throws IOException, ClassNotFoundException {
        return null;
    }
}
