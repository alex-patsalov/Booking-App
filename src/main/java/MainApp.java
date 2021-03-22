import java.io.IOException;
import java.util.*;

public class MainApp {
    public static Scanner sc = new Scanner(System.in);
    private final static FlightSearchDAO FlightSearchDAO = new FlightsDB();
    private final static FlightSearchService FSS = FlightSearchService.getInstance(FlightSearchDAO);
    public static FlightSearchController FSC = FlightSearchController.getInstance(FSS);

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
        List<Flight> allFlights = FSC.getAllFlights();
        try {
            List<Flight> dataFromDB = FSC.getDataFomDB();
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
        }

    }
    public static void searchAndBook(){

    }
    public static void cancelBooking(){

    }
    public static void showMyBookings(){

    }
    public static void main(String[] args) {
//        FSC.makeRandomFlights(10);
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

    }
}
