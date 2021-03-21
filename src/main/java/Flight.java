import java.util.Date;

public class Flight {
    private final String id = FlightCompany.generateRandomId();
    private final String cityOfDeparture = "Kyiv";
    private final String cityOfDestination;
    private final Date timeOfDeparture;
    private final Date timeOfArrival;
    private final int totalNumberOfSeats;
    private int numberOfFreeSeats;

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

    public int getNumberOfFreeSeats(){
        return this.numberOfFreeSeats;
    }

    public void setTotalNumberOfSeats(int number){
        this.numberOfFreeSeats = number;
    }

    public boolean bookSeats(int number){
        if(this.getNumberOfFreeSeats() > number){
            this.setTotalNumberOfSeats(this.getNumberOfFreeSeats() - number);
            System.out.println("The seats are booked");
            return true;
        }
        System.out.println("There is no enough space on the selected flight");
        return false;
    }
}
