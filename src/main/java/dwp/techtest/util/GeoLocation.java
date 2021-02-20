package dwp.techtest.util;

import dwp.techtest.model.Location;
import org.geotools.referencing.GeodeticCalculator;
import org.springframework.stereotype.Component;


@Component
public class GeoLocation {


    public Double getDistance(Location start , Location destination) {
        GeodeticCalculator gc = new GeodeticCalculator();
        double distance = 0.00;
        try {
            gc.setStartingGeographicPoint(start.getLongitude(),start.getLatitude());
            gc.setDestinationGeographicPoint(destination.getLongitude(), destination.getLatitude());
            distance = gc.getOrthodromicDistance();
        }catch(Exception e){
            System.out.println("Unable to calculate distance. \n Error Message: " + e.getMessage());
            return null;
        }

        double distanceInMiles = convertMetersToMiles(distance);
        return distanceInMiles;

    }

    public static Double convertMetersToMiles(double meters){
        double miles = meters*0.000621371192;
        return Math.round(miles * 100.0) / 100.0;
    }

}
