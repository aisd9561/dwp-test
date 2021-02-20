package dwp.techtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    public Location( Double latitude,Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
