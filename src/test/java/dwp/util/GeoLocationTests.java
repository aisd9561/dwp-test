package dwp.util;

import dwp.techtest.model.Location;
import dwp.techtest.model.User;
import dwp.techtest.util.Constants;
import dwp.techtest.util.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class GeoLocationTests {

    @Test
    public void testGetDistance_WhenStartingLocationAndDestinationIsOverFiftyMiles(){
        //Arrange
        Location destination = new Location( 51.803004,-1.322074);
        //Act
        double actual = GeoLocation.getDistance(Constants.LONDON,destination);
        //Assert
        Assertions.assertTrue(actual > 50);

    }

    @Test
    public void testGetDistance_WhenStartingLocationAndDestinationIsUnderFiftyMiles(){
        //Arrange
        Location destination = new Location( 51.5762232,-1.0603214);
        //Act
        double actual = GeoLocation.getDistance(Constants.LONDON,destination);
        //Assert
        Assertions.assertTrue(actual < 50);

    }

    @Test
    public void testGetDistance_WhenStartingLocationAndDestinationIsTheSame(){

        //Act
        double actual = GeoLocation.getDistance(Constants.LONDON,Constants.LONDON);
        //Assert
        double expected = 0.0;
        Assertions.assertEquals(actual, expected);

    }

    @Test
    public void testConvertMetersToMiles_WhenValueIsZero(){
        //Act
        double actual = GeoLocation.convertMetersToMiles(0);
        double expected = 0.0;
        //Assert
        Assertions.assertEquals(actual, expected);

    }
    @Test
    public void testConvertMetersToMiles_WhenValueIsGreaterThan0(){
        //Act
        double actual = GeoLocation.convertMetersToMiles(100);
        //Assert
        double expected = 0.06;
        Assertions.assertEquals(actual, expected);

    }



}
