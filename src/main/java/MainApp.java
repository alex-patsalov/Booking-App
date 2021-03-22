import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainApp {

    private final static FlightSearchDAO FlightSearchDAO = new FlightsDB();
    private final static FlightSearchService FSS = FlightSearchService.getInstance(FlightSearchDAO);
    public static FlightSearchController FSC = FlightSearchController.getInstance(FSS);

    public static HashMap<Integer, Runnable> menu = new HashMap<Integer,Runnable>(){{
        put(1, selection1());
//        put(2, selection2());
//        put(3, selection3());
//        put(4, selection4());
//        put(5, selection5());
//        put(6, selection6());
    }};
    public static void printMenu(){
        System.out.println("1. Онлайн-табло \n" +
                "2. Просмотреть информацию о рейсе \n" +
                "3. Поиск и бронировка билета \n" +
                "4. Отменить бронирование \n" +
                "5. Мои рейсы \n" +
                "6. Выход \n");
    }
    public static void showAllFLights(){
        List<Flight> allFlights = FSC.getAllFlights();
        try {
            List<Flight> dataFomDB = FSC.getDataFomDB();
            dataFomDB.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        FSC.makeRandomFlights(10);
        printMenu();
    }
}
