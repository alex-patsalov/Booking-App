import java.io.IOException;
import java.util.List;

public class FlightBookingController {
    public static FlightBookingController INSTANCE;
    public FlightBookingService FBS;

    private FlightBookingController(FlightBookingService flightBS){

        FBS = flightBS;
    }

    static FlightBookingController getInstance(FlightBookingService flightBS){
        if( FlightBookingController.INSTANCE != null) {
            return INSTANCE;
        }
        FlightBookingController.INSTANCE = new FlightBookingController(flightBS);
        return INSTANCE;
    }


    public List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException {
        return FBS.getFlightsFromDB();
    }

    public List<Flight> getAvailableFlights() throws IOException, ClassNotFoundException {
        return FBS.getAvailableFlights();
    }

    public void displayAvailableOptions() throws IOException, ClassNotFoundException {
        final List<Flight> flights = getAvailableFlights();
        System.out.println("  FLIGHT | TIME OF DEPARTURE | FROM |     TO     | SEATS | FREE ");
        for (int i =0 ; i < flights.size(); i++) {
            System.out.printf("%d.", i+1);
            flights.get(i).prettyFormat();
        }
        System.out.println("\n\n");
    }

}
