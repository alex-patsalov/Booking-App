import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable {
    @Override
    public String toString() {
        return "Booking{" +
                "flight=" + flight +
                ", passengers=" + passengers.toString() +
                ", id='" + id + '\'' +
                '}';
    }

    final Flight  flight;
  final List<Passenger> passengers;
  final String id;

    public Booking(Flight flight, List<Passenger> passengers, String id) {
        this.flight = flight;
        this.passengers = passengers;
        this.id = id;
    }

}
