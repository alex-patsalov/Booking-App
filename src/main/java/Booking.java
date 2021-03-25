import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable {
    static final long serialVersionUID = 8368658841056368402L;
    @Override
    public String toString() {
        return "Бронирование: \n" +
                "Рейс: \n" + flight.getPrettyFormat() +
                "\n" + passengers.toString() +
                "\n id: " + id + "\n"
                ;
    }

    final private Flight flight;
    final private List<Passenger> passengers;
    final private String id;

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String getId() {
        return id;
    }

    public Booking(Flight flight, List<Passenger> passengers, String id) {
        this.flight = flight;
        this.passengers = passengers;
        this.id = id;
    }

}
