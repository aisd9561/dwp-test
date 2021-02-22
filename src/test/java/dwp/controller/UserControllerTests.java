package dwp.controller;


import dwp.techtest.controller.UserController;
import dwp.techtest.model.User;
import dwp.techtest.service.UserServiceImpl;
import dwp.techtest.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static dwp.UserTestData.usersInAndAroundLondon;

public class UserControllerTests {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsersInAndAroundLondon_WhenServiceReturnsUsers() {

        //Arrange
        Mockito.when(userService.getUsersFromLocation("London", Constants.LONDON, 50))
                .thenReturn(usersInAndAroundLondon);
        //Act
        List<User> response = userController.getUsersInAndAroundLondon();

        //Assert
        Assertions.assertEquals(response, usersInAndAroundLondon);

    }
}
