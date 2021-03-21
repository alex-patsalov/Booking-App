import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearchDB implements FlightSearchDAO{

    List<Flight> allFlights = new ArrayList<>();

    @Override
    public List<Flight> getAllFlights() {
        return this.allFlights;
    }

    @Override
    public Flight getFlightById(String id) {
        List<Flight> flight = this.getAllFlights().stream().filter(f -> f.getId().equals(id)).collect(Collectors.toList());
        return flight.get(0);
    }

    @Override
    public boolean deleteFlightById(String id) throws IOException {
        List<Flight> flights = this.getAllFlights().stream().filter(f -> !f.getId().equals(id)).collect(Collectors.toList());
        this.allFlights = flights;
        saveDataToDB(flights);
        return true;
    }

    @Override
    public Flight saveFlight(Flight flight) {
        if(this.allFlights.contains(flight)){
            int index = this.allFlights.indexOf(flight);
            this.allFlights.set(index,flight);
            try {
                List<Flight> flights = this.getDataFromDB();
                flights.set(index,flight);
                this.saveDataToDB(flights);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.allFlights.add(flight);
            try {
                List<Flight> flights = this.getDataFromDB();
                flights.add(flight);
                this.saveDataToDB(flights);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return flight;
    }

    @Override
    public void saveDataToDB(List<Flight> flights) throws IOException {
        File file = new File("FlightsDB.bin");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(flights);
        oos.close();
    }

    @Override
    public List<Flight> getDataFromDB() throws IOException, ClassNotFoundException {
        File file = new File("FlightsDB.bin");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Flight> allFlightsFromDB = (List<Flight>) ois.readObject();
        System.out.println("This is data from DB:");
        allFlights.forEach(System.out::println);
        this.allFlights = allFlightsFromDB;
        ois.close();
        return allFlightsFromDB;
    }
}
