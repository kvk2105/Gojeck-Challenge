import com.gojeck.ParkingLot;
import com.gojeck.ParkingLotDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by vamshi on 7/10/16.
 */

public class ParkinglotTests extends TestCase{
    static ParkingLot parkingLot;

    @Before
    public void before(){
        System.out.println("Creating parking lot with 1 slots");
        parkingLot=new ParkingLot(1);
    }

    @Test
    public void testCreateParkingLot(){
        parkingLot=new ParkingLot(1);
        String command="create_parking_lot 1";
        Assert.assertEquals("Created a parking lot with 1 slots", ParkingLotDriver.processCommand(command));
    }

    @Test
    public void testParkSuccess(){
        parkingLot=new ParkingLot(1);
        String command="park KA01HH1234 White";
        Assert.assertEquals("Allocated slot number: 1", ParkingLotDriver.processCommand(command));
    }

    @Test
    public void testParkFailure(){
        parkingLot=new ParkingLot(1);
        String command="park KA01HH1234 White";
        ParkingLotDriver.processCommand(command);
        command="park KA01HH9876 Black";
        Assert.assertEquals("Sorry, parking lot is full", ParkingLotDriver.processCommand(command));
    }

    @Test
    public void testLeave(){
        parkingLot=new ParkingLot(1);
        String command="leave 1";
        Assert.assertEquals("Slot number 1 is free", ParkingLotDriver.processCommand(command));
    }


    @Test
    public void testRegistrationNumbersForCarsWithColour(){
        parkingLot=new ParkingLot(1);
        String command="park KA01HH1234 White";
        ParkingLotDriver.processCommand(command);
        command="registration_numbers_for_cars_with_colour White";
        assertEquals("KA01HH1234",ParkingLotDriver.processCommand(command));
    }


    @After
    public void after(){
        parkingLot=null;
        Assert.assertEquals(parkingLot, null);
    }
}

