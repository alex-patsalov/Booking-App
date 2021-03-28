import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingServiceTest {
    static FlightsDB DBF = null;
    static Flight f = new Flight();
    static File file1 = null;

    static FlightBookings DBB = null;


    public FlightBookingServiceTest() throws IOException, ClassNotFoundException {
    }
    @BeforeAll
    public static void beforeAll(){
        DBB = new FlightBookings(DBF);
    }
    @AfterAll
    public static void afterAll(){
        DBB = null;
    }

    @Test
    void getInstance() {
        FlightBookingService FBS = FlightBookingService.getInstance(DBB);

        FlightBookingService FBServ = FlightBookingService.getInstance(DBB);
        Assertions.assertEquals(FBServ, FBS);
    }
    @Test
    void dayChecking1(){
        FlightBookingService FBS = FlightBookingService.getInstance(DBB);
            boolean c1 = FBS.dayCheck(29, 2, 1996);

        Assertions.assertTrue(c1);
    }
    @Test
    void dayChecking2(){
        FlightBookingService FBS = FlightBookingService.getInstance(DBB);
        boolean c1 = FBS.dayCheck(29, 2, 1997);

        Assertions.assertFalse(c1);
    }
    @Test
    void dayChecking3(){
        FlightBookingService FBS = FlightBookingService.getInstance(DBB);
        boolean c1 = FBS.dayCheck(31, 1, 1997);

        Assertions.assertTrue(c1);
    }
    @Test
    void dayChecking4(){
        FlightBookingService FBS = FlightBookingService.getInstance(DBB);
        boolean c1 = FBS.dayCheck(31, 2, 1997);

        Assertions.assertFalse(c1);
    }
}
