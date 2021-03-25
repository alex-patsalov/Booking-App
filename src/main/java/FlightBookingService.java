import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FlightBookingService {
    private static FlightBookingService INSTANCE;
    public FlightBookingDAO FBDao;

    static Scanner sc = new Scanner(System.in);
    private FlightBookingService(FlightBookingDAO flightBooking) {

        this.FBDao =  flightBooking;
    }

    public static FlightBookingService getInstance(FlightBookingDAO flightBooking) {
        if (FlightBookingService.INSTANCE != null) {
            return INSTANCE;
        }
        FlightBookingService.INSTANCE = new FlightBookingService(flightBooking);
        return INSTANCE;
    }

    public List<Flight> getFlightsFromDB() throws IOException, ClassNotFoundException {
        return FBDao.getFlightsFromDB();
    }

    public List<Flight> getAvailableFlights() throws IOException, ClassNotFoundException {
        String destination =  getInput("Введите город назначаения:\n");
        int year = getIntFromUser("введите дату отправления. Сначала год:\n");
        int month;
        do {
            month = getIntFromUser("месяц:\n");
        }
        while (!monthCheck(month));
        int day;
        do {
            day = getIntFromUser("день месяца\n");
        }
        while (!dayCheck(day, month, year));
        String m;
        if (month<10) {
             m = String.format("0%d", month);
        } else {
             m = String.format("%d", month);
        }
        String date = String.format("%d-%s-%d", year, m , day);
        int passengers = getIntFromUser("Введите количество пассажиров:\n");

        return FBDao.getAvailableFlights(destination,date,passengers);
    }

    public static String getInput(String message) {
        System.out.print(message);
        return sc.next();
    }
    public static int getIntFromUser(String message) {
        String nStr;
        do {
            nStr = getInput(message + "\n");
        }
        while (!isParsableInt(nStr));
        return Integer.parseInt(nStr);
    }
    public static boolean monthCheck(int month) {
        try {
            if (month > 12 || month < 1) {
                throw new IllegalArgumentException("неправильный месяц");
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean dayCheck(int day, int month, int year) {
        boolean leap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                leap = year % 400 == 0;
            } else
                leap = true;
        }

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day > 31 || day < 1) {
                    return false;
                }
                break;
            case 2:
                if (leap) {
                    if (day > 29 || day < 1) {
                        return false;
                    }
                } else {
                    if (day > 28 || day < 1) {
                        return false;
                    }
                }
                break;
            default:
                if (day > 30 || day < 1) {
                    return false;
                }
                break;
        }
        return true;
    }
    public static boolean isParsableInt(String choice) {
        try {
            Integer.parseInt(choice);
        } catch (IllegalArgumentException error) {
            return false;
        }
        return true;
    }

    public void selectAndBook(List <Flight> flights) throws IOException, ClassNotFoundException {
        int number;
        do {
            number = getIntFromUser("Выберите рейс по порядковому номеру");
        }
        while (number<1 || number>flights.size());
        System.out.println("Вы выбрали \n");
        flights.get(number -1).prettyFormat();
        System.out.println("\n");
        Flight choosenFlight = flights.get(number -1);
        Booking booking = this.FBDao.saveFlightToBookingList(choosenFlight);
    }
}
