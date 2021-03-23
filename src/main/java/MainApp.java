import java.io.IOException;
import java.util.List;

public class MainApp {
    public static FlightSearchController FSC;
    public static FlightBookingController FBC = new FlightBookingController();
    static {
        FSC = new FlightSearchController();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FSC.makeRandomFlights(100);
        List<Flight> allFlights = FSC.getAllFlights();
        try {
            List<Flight> dataFomDB = FSC.getDataFomDB();
            dataFomDB.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n\n");
        FBC.displayAvailableOptions("Paris", "2021-03-24", 2);
    }
}
