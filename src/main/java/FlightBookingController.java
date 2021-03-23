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

    public List<Flight> getAvailableFlights(String destination, String date, int passengers) throws IOException, ClassNotFoundException {
        return FBS.getAvailableFlights(destination,date,passengers);
    }

    public void displayAvailableOptions(String destination, String date, int passengers) throws IOException, ClassNotFoundException {
        final List<Flight> flights = getAvailableFlights(destination,date,passengers);
        for (int i =0 ; i < flights.size(); i++) {
            System.out.printf("%d. %s\n", i+1, flights.get(i));
        }
    }

}
