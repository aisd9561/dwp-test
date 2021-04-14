package dwp.techtest.util;

import dwp.techtest.model.Location;
import org.geotools.referencing.GeodeticCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class GeoLocation {

    private static final Logger LOGGER= LoggerFactory.getLogger(GeoLocation.class);

    public Double getDistance(Location start , Location destination) {
        GeodeticCalculator gc = new GeodeticCalculator();
        double distance = 0.00;
        try {
            gc.setStartingGeographicPoint(start.getLongitude(),start.getLatitude());
            gc.setDestinationGeographicPoint(destination.getLongitude(), destination.getLatitude());
            distance = gc.getOrthodromicDistance();
        }catch(Exception e){
            LOGGER.warn("Unable to calculate distance." +  e.getMessage());
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
