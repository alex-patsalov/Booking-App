import java.util.Date;

public class Flight {
    private final String id = FlightCompany.generateRandomId();
    private final String cityOfDeparture = "Kyiv";
    private final String cityOfDestination;
    private final Date timeOfDeparture;
    private final Date timeOfArrival;
    private final int totalNumberOfSeats;
    private final int numberOfFreeSeats;

    public Flight(Date timeOfDeparture, String cityOfDestination, Date timeOfArrival, int totalNumberOfSeats, int numberOfFreeSeats) {
        this.cityOfDestination = cityOfDestination;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.numberOfFreeSeats = numberOfFreeSeats;
    }
    public String getId(){
        return this.id;
    }


}
