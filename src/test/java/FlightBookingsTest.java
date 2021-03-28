import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

class FlightBookingsTest {
    static FlightsDB DBF = null;
    static Flight f = new Flight();
    static File file1 = null;

    static FlightBookings DBB = null;
    static File file2 = null;
    ArrayList<Booking> bookings = new ArrayList<>();
    Passenger p = new Passenger("Peter", "Petrenko");
    ArrayList<Passenger> passengers = new ArrayList<>();
    List<Flight> flights = DBF.getDataFromDB();

    FlightBookingsTest() throws IOException, ClassNotFoundException {
    }


    @BeforeAll
    public static void beforeAll() {
        DBF = new FlightsDB();
        DBB = new FlightBookings(DBF);
        file1 = new File("FlightsDBforTests.bin");
        file2 = new File("BookingsForTests.bin");
    }

    @AfterAll
    public static void afterAll() {
        DBF = null;
        DBB = null;
        file1.delete();
        file2.delete();

    }

    @Test
    void saveBooking() throws IOException, ClassNotFoundException {
        passengers.add(p);
        Booking booking = new Booking( flights.get(0), passengers  , "7cb5841f-1dd7-4e40-af31-98fb2ce33b45");
        bookings.add(booking);
        String real = bookings.toString();
        String expected = "[Бронирование: \n" +
        "Рейс: \n" + flights.get(0).getPrettyFormat() +
        "\n" + "[Пассажир: имя: Peter, фамилия: Petrenko\n]" +
        "\n id: " + "7cb5841f-1dd7-4e40-af31-98fb2ce33b45" + "\n]";

        Assertions.assertEquals(real,expected);
    }
    @Test
    void writeAndReadBookingFromFile() throws IOException, ClassNotFoundException {
        passengers.add(p);
        Booking booking = new Booking( flights.get(0), passengers  , "7cb5841f-1dd7-4e40-af31-98fb2ce33b45");
        bookings.add(booking);

        FileOutputStream fos = new FileOutputStream(file2);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bookings);
        oos.close();
        FileInputStream fis = new FileInputStream(file2);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Booking> allBookings = (ArrayList<Booking>) ois.readObject();
        ois.close();
        Assertions.assertEquals(bookings.toString(),allBookings.toString());
    }

    @Test
    void cancelBooking(){
        passengers.add(p);
        String id = "7cb5841f-1dd7-4e40-af31-98fb2ce33b45";
        Booking booking = new Booking( flights.get(0), passengers  , id);
        bookings.add(booking);
        List<Booking> updatedBookings = this.bookings.stream().filter(b -> !(b.getId().equals(id))).collect(Collectors.toList());
        String expected = "[]";
        Assertions.assertEquals(updatedBookings.toString(),expected);
    }
    @Test
    void updateFlight() {
        List<Flight> allFlights = new ArrayList<>(flights) ;
        Flight flight = allFlights.get(0) ;
        int startingNumberOfSeats = flight.getNumberOfFreeSeats();
        int seats = 1;
        System.out.println(allFlights.get(0)) ;
        Flight fl = allFlights.stream()
                .filter(fli -> fli.getId().equals(flight.getId()))
                .findFirst().orElse(null);
        if (fl != null) {
            fl.setTotalNumberOfFreeSeats(fl.getNumberOfFreeSeats() - seats);
        }
        allFlights.forEach(fli -> {
            if (fli.getId().equals(fl.getId())) {
                fli = fl;
            }
        });
        int finalNumberOfSeats = allFlights.get(0).getNumberOfFreeSeats(); ;
        System.out.println(allFlights.get(0)) ;
        int real = startingNumberOfSeats - finalNumberOfSeats  ;
        int expected = 1;
        Assertions.assertEquals(real, expected);

    }
}
