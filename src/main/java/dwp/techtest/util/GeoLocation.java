package dwp.techtest.util;

import dwp.techtest.model.Location;
import org.geotools.referencing.GeodeticCalculator;
import org.springframework.stereotype.Component;


@Component
public class GeoLocation {

    public static Double getDistance(Location start , Location destination) {
        GeodeticCalculator gc = new GeodeticCalculator();
        gc.setStartingGeographicPoint(start.getLongitude(),start.getLatitude());
        gc.setDestinationGeographicPoint(destination.getLongitude(), destination.getLatitude());
        double distance = gc.getOrthodromicDistance();
        double distanceInMiles = convertMetersToMiles(distance);
        return distanceInMiles;

    }

    public static Double convertMetersToMiles(double meters){
        double miles = meters*0.000621371192;
        return Math.round(miles * 100.0) / 100.0;
    }

}
