import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable {
    @Override
    public String toString() {
        return "Бронирование: \n" +
                "Рейс: \n" + flight.getPrettyFormat() +
                "\n" + passengers.toString() +
                "\n id: " + id + "\n"
                ;
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
