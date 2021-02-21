package dwp.techtest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "first_name",
        "last_name",
        "email",
        "ip_address",
        "latitude",
        "longitude"
})
public class User {

   

    /**
     * @param firstName
     * @param lastName
     * @param latitude
     * @param ipAddress
     * @param id
     * @param email
     * @param longitude
     */
    public User(Long id, String firstName, String lastName, String email, String ipAddress, Double latitude, Double longitude) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("ip_address")
    private String ipAddress;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                "} \n";
    }
}