import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FlightBookings implements FlightBookingDAO {
    List<Flight> allFlights = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();
    int selectedNumberOfSeats;
    FlightSearchDAO flightDB;

    FlightBookings(FlightSearchDAO flDB) {
        this.flightDB = flDB;
        try {
            this.bookings = (ArrayList<Booking>) getBookingsFromDB();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<Flight> getAllFlights() {
        return this.allFlights;
    }

    @Override
    public List<Flight> getAvailableFlights(String destination, String date, int passengers) throws IOException, ClassNotFoundException {
        this.getFlightsFromDB();
        this.getBookingsFromDB();
        this.selectedNumberOfSeats = passengers;
        System.out.println("Options for booking:\n");
        List<Flight> flights = this.allFlights.stream().filter(fl -> (destination.equals(fl.getCityOfDestination())
                && (fl.getTimeOfDeparture().substring(0, 10)).equals(date)) && (passengers < fl.getNumberOfFreeSeats()))
                .collect(Collectors.toList());

        return flights;
    }

    @Override
    public boolean deleteFlightByIdFromBookingList(String id) throws IOException {
        return false;
    }

    @Override
    public Booking saveFlightToBookingList(Flight flight) throws IOException {

        System.out.println("количество выбранных мест: " + selectedNumberOfSeats);
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int seat = 1; seat <= selectedNumberOfSeats; seat++) {
            String name = FlightBookingService.getInput("Ввведите имя " + seat + " пассажира");
            String surname = FlightBookingService.getInput("Ввведите фамилию " + seat + " пассажира");
            Passenger p = new Passenger(name, surname);
            passengers.add(p);
        }
        String id = UUID.randomUUID().toString();
        Booking booking = new Booking(flight, passengers, id);
        bookings.add(booking);
        this.saveBookingsToDB(bookings);
        System.out.printf("Бронирование сохранено: %s\n", booking.toString());
        this.updateFlight(flight, selectedNumberOfSeats);
        return booking;
    }

    public void updateFlight(Flight flight, int seats) throws IOException {
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
        flightDB.saveDataToDB(allFlights);
        try {
            flightDB.getDataFromDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveBookingsToDB(List<Booking> bookings) throws IOException {
        File file = new File("bookingsDB.bin");
        FileOutputStream fos = new FileOutputStream(file);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(bookings);
        }
    }

    @Override
    public List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException {
        File file = new File("FlightsDB.bin");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Flight> allFlightsFromDB = (List<Flight>) ois.readObject();
        this.allFlights = allFlightsFromDB;
        ois.close();
        return allFlightsFromDB;
    }

    @Override
    public List<Booking> getBookingsFromDB() throws IOException, ClassNotFoundException {

        File file = new File("bookingsDB.bin");
        FileInputStream fis = new FileInputStream(file);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Booking> allBookingsFromDB = (ArrayList<Booking>) ois.readObject();
            this.bookings = allBookingsFromDB;
            return allBookingsFromDB;
        }
        catch (FileNotFoundException e) {
            this.saveBookingsToDB(bookings);
            return this.bookings;
        }
    }

    @Override
    public void displayMyBookings() throws IOException, ClassNotFoundException {
        List<Booking> myFlights = getBookingsFromDB();
        System.out.println("Мои бронирования: ");
        myFlights.forEach(fl -> {
            System.out.println(fl.toString());
        });
    }

    @Override
    public void cancelBooking(String id){
        Booking booking = Objects.requireNonNull(this.bookings.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null));;
        List<Booking> updatedBookings = this.bookings.stream().filter(b -> !(b.getId().equals(id))).collect(Collectors.toList());
        try {
            saveBookingsToDB(updatedBookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bookings = (ArrayList<Booking>) updatedBookings;
        int numberOfSeats = booking.getPassengers().size();
        try {
            updateFlight(booking.getFlight(), -numberOfSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }

    };
}
