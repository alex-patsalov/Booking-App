import java.io.IOException;
import java.util.List;

public class MainApp {
    public static FlightSearchController FSC;

    static {
        FSC = new FlightSearchController();
    }

    public static void main(String[] args) {
        FSC.makeRandomFlights(100);
        List<Flight> allFlights = FSC.getAllFlights();
        try {
            List<Flight> dataFomDB = FSC.getDataFomDB();
            dataFomDB.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
