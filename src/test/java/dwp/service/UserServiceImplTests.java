package dwp.service;

import dwp.techtest.TechTestApplication;
import dwp.techtest.api.UsersApi;
import dwp.techtest.model.User;
import dwp.techtest.service.UserServiceImpl;
import dwp.techtest.util.Constants;
import dwp.techtest.util.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import static dwp.UserTestData.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTests {

    @Mock
    UsersApi usersApi;

    @Spy
    GeoLocation geoLocation;

    @InjectMocks
    UserServiceImpl userService;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetUsersFromLocation_WhenUsersHaveInvalidLocationData(){
        //Arrange
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(usersWithInvalidLocationData);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response, usersWithinLondon);

    }

    @Test
    public void testGetUsersFromLocation_WhenAllUsersHaveValidLocationDataNoDuplicates(){
        //Arrange
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(usersWithValidLocationDataNoDuplicates);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response, usersInAndAroundLondon);

    }

    @Test
    public void testGetUsersFromLocation_WhenAllUsersHaveValidLocationDataWithDuplicates(){
        //Arrange
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(usersWithValidLocationDataWithDuplicates);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response, usersInAndAroundLondon);

    }

    @Test
    public void testGetUsersFromLocation_WhenAllTypesOfUsersReturned(){

        //Arrange
        Mockito.when(usersApi.getUserByCity("London"))
                .thenReturn(usersWithinLondon);
        Mockito.when(usersApi.getUsers()).thenReturn(allUsers);
        //Act
        List<User> response = userService.getUsersFromLocation("London",Constants.LONDON,50);

        //Assert
        Assertions.assertEquals(response, usersInAndAroundLondon);

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
        Assertions.assertEquals(response, usersWithinLondon);

    }

}
