import java.io.Serializable;
import java.util.Random;

public class Flight implements Serializable {
    private final String id = FlightCompany.generateRandomId();
    private final String cityOfDeparture = "Kyiv";
    private final String cityOfDestination = FlightDestination.getRandomDestination();
    private final String timeOfDeparture = FlightDestination.getRandomTimeOfDeparture();
    private final int totalNumberOfSeats = this.getRandomNumberOfSeats();
    private int numberOfFreeSeats = this.getRandomNumberOfFreeSeats();
    private static final Random random = new Random();

    public String getId(){
        return this.id;
    }

    public int getNumberOfFreeSeats(){
        return this.numberOfFreeSeats;
    }
    public String getTimeOfDeparture(){return this.timeOfDeparture;}
    public String getCityOfDeparture(){return this.cityOfDeparture;}
    public String getCityOfDestination(){return this.cityOfDestination;}
    public int getTotalNumberOfSeats(){return this.totalNumberOfSeats;}
    public int getRandomNumberOfSeats(){
        return random.nextInt(200) + 100;
    }

    public int getRandomNumberOfFreeSeats(){
        return random.nextInt(5);
    }

    public void setTotalNumberOfFreeSeats(int number){
        this.numberOfFreeSeats = number;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Flight that = (Flight) obj;

        return this.getId().equals(that.getId());
    }

    public boolean bookSeats(int number){
        if(this.getNumberOfFreeSeats() > number){
            this.setTotalNumberOfFreeSeats(this.getNumberOfFreeSeats() - number);
            System.out.println("The seats are booked");
            return true;
        }
        System.out.println("There is no enough space on the selected flight");
        return false;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", cityOfDeparture='" + cityOfDeparture + '\'' +
                ", cityOfDestination='" + cityOfDestination + '\'' +
                ", timeOfDeparture=" + timeOfDeparture +
                ", totalNumberOfSeats=" + totalNumberOfSeats +
                ", numberOfFreeSeats=" + numberOfFreeSeats +
                '}';
    }
    public void prettyFormat(){
        System.out.printf("%6s | %17s | %s | %-10s | %-5d | %-4d \n", this.getId(), this.timeOfDeparture, this.cityOfDeparture, this.cityOfDestination, this.totalNumberOfSeats, this.getNumberOfFreeSeats());
    }
    public String getPrettyFormat(){
        return String.format("%6s | %17s | %s | %-10s \n", this.getId(), this.timeOfDeparture, this.cityOfDeparture, this.cityOfDestination);
    }
}
