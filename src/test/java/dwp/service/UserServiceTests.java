package dwp.service;

import dwp.techtest.TechTestApplication;
import dwp.techtest.api.UsersApi;
import dwp.techtest.model.Location;
import dwp.techtest.model.User;
import dwp.techtest.service.UserServiceImpl;
import dwp.techtest.util.Constants;
import dwp.techtest.util.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = TechTestApplication.class)
public class UserServiceTests {

    @Mock
    UsersApi usersApi;

    @Spy
    GeoLocation geoLocation;

    @InjectMocks
    UserServiceImpl userService;

    Location watford = new Location(51.6613536,-0.440529);
    Location crawley = new Location(51.1197415,-0.2180565);
    Location oxford = new Location(51.6465698,0.985909);
    Location nuneham = new Location(51.693465,-1.207614);
    Location blenheim = new Location(51.8467258,-1.3877292);
    Location banbury = new Location(52.0068805,-1.5701804);
    Location birmingham = new Location(52.4774169,-1.9336706);
    Location germany =  new Location(49.466899,9.8031);
    Location invalid =  new Location(-111498.5701804,1287217.67);
    Location invalid2 =  new Location(-159.5219561,1598191.159);

    User userInLondon = new User((long)1,"","","","",Constants.LONDON_LATITUDE,Constants.LONDON_LONGITUDE);
    User userInLondon2 = new User((long)2,"","","","",Constants.LONDON_LATITUDE,Constants.LONDON_LONGITUDE);
    User userWithin50MilesOfLondon = new User((long)3,"","","","",watford.getLatitude(),watford.getLongitude());
    User userWithin50MilesOfLondon2 = new User((long)4,"","","","",crawley.getLatitude(),crawley.getLongitude());
    User userWithin50MilesOfLondon3 = new User((long)5,"","","","",oxford.getLatitude(),oxford.getLongitude());
    User userWithin50MilesOfLondon4 = new User((long)6,"","","","",nuneham.getLatitude(),nuneham.getLongitude());
    User userOver50MilesOfLondon = new User((long)7,"","","","",blenheim.getLatitude(),blenheim.getLongitude());
    User userOver50MilesOfLondon2 = new User((long)8,"","","","",banbury.getLatitude(),banbury.getLongitude());
    User userOver50MilesOfLondon3 = new User((long)9,"","","","",birmingham.getLatitude(),birmingham.getLongitude());
    User userFromGermany = new User((long)10,"","","","",germany.getLatitude(),germany.getLongitude());
    User userWithInvalidLocationData2 = new User((long)11,"","","","",invalid.getLatitude(),invalid.getLongitude());
    User userWithInvalidLocationData3 = new User((long)12,"","","","",invalid2.getLatitude(),invalid2.getLongitude());

    List<User> allUsers = new ArrayList<>(
            Arrays.asList(
                    userInLondon,userInLondon2,userWithin50MilesOfLondon,userWithin50MilesOfLondon2,
                    userWithin50MilesOfLondon3,userWithin50MilesOfLondon4,
                    userOver50MilesOfLondon,userOver50MilesOfLondon2,userOver50MilesOfLondon3,
                    userFromGermany,userWithInvalidLocationData2,userWithInvalidLocationData3
            )
    );

    List<User> usersWithinLondon = new ArrayList<>(Arrays.asList(userInLondon,userInLondon2));

    List<User> usersInAndAroundLondon = new ArrayList<>(
                    Arrays.asList(
                            userInLondon,userInLondon2,
                            userWithin50MilesOfLondon,userWithin50MilesOfLondon2,
                            userWithin50MilesOfLondon3,userWithin50MilesOfLondon4
                    )
            );


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetUsersFromLocation_WhenUsersHaveInvalidLocationData(){
        List<User> mockedResponse = new ArrayList<>(
                Arrays.asList(
                        userWithInvalidLocationData2,userWithInvalidLocationData3
                ));
        //Arrange
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(mockedResponse);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response,usersWithinLondon);

    }

    @Test
    public void testGetUsersFromLocation_WhenAllUsersHaveValidLocationDataNoDuplicates(){
        //Arrange
        List<User> mockedResponse = new ArrayList<>(
                Arrays.asList(
                        userWithin50MilesOfLondon,userWithin50MilesOfLondon2,
                        userWithin50MilesOfLondon3,userWithin50MilesOfLondon4,
                        userOver50MilesOfLondon,userOver50MilesOfLondon2,userOver50MilesOfLondon3
                )
        );
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(mockedResponse);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response,usersInAndAroundLondon);

    }

    @Test
    public void testGetUsersFromLocation_WhenAllUsersHaveValidLocationDataWithDuplicates(){
        //Arrange
        List<User> mockedResponse = new ArrayList<>(
                Arrays.asList(
                        userInLondon,userInLondon2,
                        userWithin50MilesOfLondon,userWithin50MilesOfLondon2,
                        userWithin50MilesOfLondon3,userWithin50MilesOfLondon4,
                        userOver50MilesOfLondon,userOver50MilesOfLondon2,userOver50MilesOfLondon3
                )
        );
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(mockedResponse);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response,usersInAndAroundLondon);

    }

    @Test
    public void testGetUsersFromLocation_WhenNullUsersReturnedFromUsersApi(){
        //Arrange

        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(null);
        Mockito.when(usersApi.getUsers()).thenReturn(null);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response,null);

    }
    @Test
    public void testGetUsersFromLocation_WhenEmptyUsersReturnedFromUsersApi(){
        //Arrange
        List<User> emptyUsers = new ArrayList<>();

        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(emptyUsers);
        Mockito.when(usersApi.getUsers()).thenReturn(emptyUsers);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response,null);

    }

    @Test
    public void testGetUsersFromLocation_WhenAllUsersIsNullReturnedFromUsersApi(){
        //Arrange
        List<User> emptyUsers = new ArrayList<>();
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(emptyUsers);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response,usersWithinLondon);

    }

}
