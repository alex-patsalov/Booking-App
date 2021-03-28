import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FlightBookingControllerTest {
    static FlightsDB DB = null;

    static FlightBookings BDB = null;
    static FlightBookingService FBServ = null;
    static FlightBookingController FBC = null;
    @BeforeAll
    public static void beforeAll(){
        DB = new FlightsDB();
        BDB = new FlightBookings(DB);
        FBServ =  FlightBookingService.getInstance(BDB);
         FBC = FlightBookingController.getInstance(FBServ);
    }
    @AfterAll
    public static void afterAll(){
        FBServ = null;
        FBC = null;
    }

    @Test
    void getInstance() {
        FlightBookingController FBContr = FlightBookingController.getInstance(FBServ);
        Assertions.assertEquals(FBContr, FBC);
    }
}
