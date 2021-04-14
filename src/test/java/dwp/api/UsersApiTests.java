package dwp.api;

import dwp.techtest.api.UsersApi;
import dwp.techtest.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static dwp.UserTestData.userInLondon;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class UsersApiTests {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private UsersApi usersApi;

    WebClient.RequestHeadersUriSpec uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
    WebClient.RequestHeadersSpec headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
    WebClient.ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
    ParameterizedTypeReference<List<User>> schemaType = new ParameterizedTypeReference<List<User>>() {
    };

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(webClient.get()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri(ArgumentMatchers.<String>notNull())).thenReturn(headersSpecMock);
        Mockito.when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
    }

    @Test
    public void testWebClient_GetUsers_OnSuccessfulResponse() {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(userInLondon);

        Mockito.when(responseSpecMock.bodyToMono( schemaType ))
                .thenReturn(Mono.just(users));

        //Act
        List<User> res = usersApi.getUsers();

        //Assert
        Assertions.assertEquals(res, users);

    }

    @Test
    public void testWebClientGetUsers_OnUnsuccessfulResponse() {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(userInLondon);

        Mockito.when(responseSpecMock.bodyToMono( schemaType ))
                .thenThrow(WebClientResponseException.class);
        //Act && Assert
        assertThrows(WebClientResponseException.class, () -> {
            usersApi.getUsers();
        });

    }

    @Test
    public void testWebClientGetUsersByCity_OnSuccessfulResponse() {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(userInLondon);

        Mockito.when(responseSpecMock.bodyToMono( schemaType ))
                .thenReturn(Mono.just(users));

        //Act
        List<User> res = usersApi.getUserByCity("city");

        //Assert
        Assertions.assertEquals(res, users);

    }

    @Test
    public void testWebClientGetUsersByCity_OnUnsuccessfulResponse() {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(userInLondon);

        Mockito.when(responseSpecMock.bodyToMono( schemaType ))
                .thenThrow(WebClientResponseException.class);

        //Act && Assert
        assertThrows(WebClientResponseException.class, () -> {
            usersApi.getUserByCity("");
        });
    }


}
