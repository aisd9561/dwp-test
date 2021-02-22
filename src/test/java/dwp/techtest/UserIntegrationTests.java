package dwp.techtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dwp.techtest.api.UsersApi;
import dwp.techtest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

import static dwp.UserTestData.allUsers;
import static dwp.UserTestData.usersWithinLondon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UsersApi usersApi;


    @Test
    public void getUsers_WhenDWPServiceIsLive() throws Exception {

        //Arrange
        when(usersApi.getUserByCity("London")).thenReturn(usersWithinLondon);
        when(usersApi.getUsers()).thenReturn(allUsers);
        //Act && Assert
        MvcResult result = this.mockMvc.perform(get("/users/london")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();


        ObjectMapper mapper = new ObjectMapper();
        List<User> parsedResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {
        });
        assertEquals(parsedResponse.size(), 6);
    }

    @Test
    public void getUsers_WhenDWPServiceIsLiveButReturnsNullUsers() throws Exception {

        //Arrange
        when(usersApi.getUserByCity("London")).thenReturn(null);
        when(usersApi.getUsers()).thenReturn(null);
        //Act && Assert
        MvcResult result = this.mockMvc.perform(get("/users/london")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertEquals(result.getResponse().getContentAsString(), "");
    }

    @Test
    public void getUsers_WhenDWPServiceIsLiveAndGetUserByCityReturnsNullUsers() throws Exception {

        //Arrange
        when(usersApi.getUserByCity("London")).thenReturn(null);
        when(usersApi.getUsers()).thenReturn(allUsers);
        //Act && Assert
        MvcResult result = this.mockMvc.perform(get("/users/london")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<User> parsedResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {
        });
        //Assert
        assertEquals(parsedResponse.size(), 6);
    }

    @Test
    public void getUsers_WhenDWPServiceIsLiveAndGetUsersReturnsNullUsers() throws Exception {

        //Arrange
        when(usersApi.getUserByCity("London")).thenReturn(usersWithinLondon);
        when(usersApi.getUsers()).thenReturn(null);
        //Act && Assert
        MvcResult result = this.mockMvc.perform(get("/users/london")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<User> parsedResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {
        });
        //Assert
        assertEquals(parsedResponse.size(), 2);
    }

    @Test
    public void getUsers_WhenDWPServiceIsDown() throws Exception {

        //Arrange
        RestClientResponseException restClientResponseException =
                new RestClientResponseException("user service is down", 500, "contact owner for more details", null, null, null);
        when(usersApi.getUserByCity("London")).thenThrow(restClientResponseException);
        when(usersApi.getUsers()).thenThrow(restClientResponseException);

        //Act && Assert
        MvcResult result = this.mockMvc.perform(get("/users/london")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError()).andReturn();


    }


}
