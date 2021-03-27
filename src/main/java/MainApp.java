import java.io.IOException;
import java.util.*;

public class MainApp {

    public static Scanner sc = new Scanner(System.in);
    private final static FlightSearchDAO FlightSearchDAO = new FlightsDB();
    private final static FlightSearchService FSS = FlightSearchService.getInstance(FlightSearchDAO);
    public static FlightSearchController FSC = FlightSearchController.getInstance(FSS);
    private final static FlightBookingDAO flightBooking = new FlightBookings(FlightSearchDAO);
    public static FlightBookingService FBS = FlightBookingService.getInstance(flightBooking);
    public static FlightBookingController FBC = FlightBookingController.getInstance(FBS);

    public static HashMap<Integer, Runnable> menu = new HashMap<Integer,Runnable>(){{
        put(1, MainApp::showAllFLights);
        put(2, MainApp::showFLightById);
        put(3, MainApp::searchAndBook);
        put(4, MainApp::cancelBooking);
        put(5, MainApp::showMyBookings);
    }};
    public static void printMenu(){
        System.out.println("1. Онлайн-табло \n" +
                "2. Просмотреть информацию о рейсе \n" +
                "3. Поиск и бронировка билета \n" +
                "4. Отменить бронирование \n" +
                "5. Мои рейсы \n" +
                "6. Выход");
    }

    public static void showAllFLights(){
//        List<Flight> allFlights = FSC.getAllFlights();
        try {
            List<Flight> dataFromDB = FSC.getDataFromDB();
            dataFromDB.forEach(Flight::prettyFormat);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void showFLightById(){
        System.out.print("Номер рейса: ");
        String buffer = sc.nextLine();
        String id = sc.nextLine();
        try {
            FSC.getInfoAboutFlight(id.toUpperCase());
        } catch (IndexOutOfBoundsException e){
            System.out.println("Такого рейса нет...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void searchAndBook(){
        try {
            FBC.displayAvailableOptions();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void cancelBooking(){
        FBC.cancelBooking();
    }
    public static void showMyBookings(){
        try {
            FBC.displayMyBookings();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FSC.makeRandomFlights(100);
        for(; ;){
            printMenu();
            System.out.print("Выбери пункт: ");
            int mainMenuSelection = sc.nextInt();
            if(mainMenuSelection == 6) break;
            try{
                menu.get(mainMenuSelection).run();
            } catch (NullPointerException e){
                System.out.println("Мимо, попробуй еще...");
            }
        }

        System.out.println("\n\n\n\n");

//        FBC.displayAvailableOptions("Paris", "2021-03-24", 2);
    }
}
