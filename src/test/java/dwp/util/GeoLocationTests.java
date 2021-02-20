package dwp.util;


import dwp.techtest.TechTestApplication;
import dwp.techtest.model.Location;
import dwp.techtest.util.Constants;
import dwp.techtest.util.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

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

    static Stream<Arguments> location_values()  {
        Location watford = new Location(51.6613536,-0.440529);
        Location crawley = new Location(51.1197415,-0.2180565);
        Location oxford = new Location(51.6465698,0.985909);
        Location birmingham = new Location(52.4774169,-1.9336706);
        Location banbury = new Location(52.0068805,-1.5701804);
        Location blenheim = new Location(51.8467258,-1.3877292);
        Location nuneham = new Location(51.693465,-1.207614);


        return Stream.of(
                Arguments.of("Watford",watford, 17.39 ),
                Arguments.of("Crawley",crawley,27.31),
                Arguments.of("Oxford",oxford,48.48 ),
                Arguments.of("Nuneham",nuneham, 48.59),
                Arguments.of("Blenheim",blenheim ,59.33),
                Arguments.of("Banbury",banbury, 71.15),
                Arguments.of("Birmingham",birmingham ,102.36)
        );
    }

    @MethodSource("location_values")
    @ParameterizedTest(name="testGetDistance_From{0}")
    public void testLocations(String place , Location location, double expected){

            double actual = geoLocation.getDistance(Constants.LONDON,location);
            Assertions.assertEquals(actual,expected);

    }



}
