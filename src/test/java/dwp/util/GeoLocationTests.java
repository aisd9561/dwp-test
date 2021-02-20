package dwp.util;


import dwp.techtest.TechTestApplication;
import dwp.techtest.model.Location;
import dwp.techtest.util.Constants;
import dwp.techtest.util.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(classes = TechTestApplication.class)
public class GeoLocationTests {

    @Autowired
    GeoLocation geoLocation ;


    @Test
    public void testGetDistance_WhenStartingLocationAndDestinationIsOverFiftyMiles(){
        //Arrange
        Location destination = new Location( 51.803004,-1.322074);
        //Act
        double actual = geoLocation.getDistance(Constants.LONDON,destination);
        //Assert
        Assertions.assertTrue(actual > 50);

    }

    @Test
    public void testGetDistance_WhenStartingLocationAndDestinationIsUnderFiftyMiles(){
        //Arrange
        Location destination = new Location( 51.5762232,-1.0603214);
        //Act
        double actual = geoLocation.getDistance(Constants.LONDON,destination);
        //Assert
        Assertions.assertTrue(actual < 50);

    }

    @Test
    public void testGetDistance_WhenStartingLocationAndDestinationIsTheSame(){

        //Act
        double actual = geoLocation.getDistance(Constants.LONDON,Constants.LONDON);
        //Assert
        double expected = 0.0;
        Assertions.assertEquals(actual, expected);

    }

    @Test
    public void testGetDistance_WhenInputIsInvalid(){

        Double result = geoLocation.getDistance(new Location(156451.0145939,62625265.2727),new Location(145949.0145939,62625265.2727));
        Assertions.assertEquals(result,null);

    }

    @Test
    public void testConvertMetersToMiles_WhenValueIsZero(){
        //Act
        double actual = geoLocation.convertMetersToMiles(0);
        double expected = 0.0;
        //Assert
        Assertions.assertEquals(actual, expected);

    }
    @Test
    public void testConvertMetersToMiles_WhenValueIsGreaterThan0(){
        //Act
        double actual = geoLocation.convertMetersToMiles(100);
        //Assert
        double expected = 0.06;
        Assertions.assertEquals(actual, expected);

    }



}
