package dwp.api;

import dwp.techtest.api.UsersApi;
import dwp.techtest.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class UsersApiTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UsersApi usersApi;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers_OnSuccessfulResponse(){
        //Arrange
        List<User> users = new ArrayList<>();
        User user1 = new User((long) 1);
        users.add(user1);
        ResponseEntity<List<User>> mockResponse = new ResponseEntity<List<User>>(users,HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),Mockito.any(),Mockito.any(),
                Mockito.any(ParameterizedTypeReference.class),Mockito.any(User[].class))
        ).thenReturn(mockResponse);
        //Act
        List<User> res = usersApi.getUsers();

        //Assert
        Assertions.assertEquals(res, users);

    }

    @Test
    public void testGetUsers_OnUnsuccessfulResponse(){
        //Arrange
        List<User> users = new ArrayList<>();
        User user1 = new User((long) 1);
        users.add(user1);

        ResponseEntity<List<User>> mockResponse = new ResponseEntity<List<User>>((List<User>) null,HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),Mockito.any(),Mockito.any(),
                Mockito.any(ParameterizedTypeReference.class),Mockito.any(User[].class))
        ).thenThrow(RestClientException.class);
        //Act && Assert
        assertThrows(RestClientException.class,() -> {
            usersApi.getUsers();
        });


    }
    @Test
    public void testGetUsersByCity_OnSuccessfulResponse(){
        //Arrange
        List<User> users = new ArrayList<>();
        User user1 = new User((long) 1);
        users.add(user1);

        ResponseEntity<List<User>> mockResponse = new ResponseEntity<List<User>>(users,HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),Mockito.any(),Mockito.any(),
                Mockito.any(ParameterizedTypeReference.class),Mockito.any(User[].class))
        ).thenReturn(mockResponse);
        //Act
        List<User> res = usersApi.getUserByCity("city");

        //Assert
        Assertions.assertEquals(res, users);



    }

    @Test
    public void testGetUsersByCity_OnUnsuccessfulResponse(){
        //Arrange
        List<User> users = new ArrayList<>();
        User user1 = new User((long) 1);
        users.add(user1);

        ResponseEntity<List<User>> mockResponse = new ResponseEntity<List<User>>((List<User>) null,HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),Mockito.any(),Mockito.any(),
                Mockito.any(ParameterizedTypeReference.class),Mockito.any(User[].class))
        ).thenThrow(RestClientException.class);
        //Act && Assert
        assertThrows(RestClientException.class,() -> {
            usersApi.getUsers();
        });


    }


}
