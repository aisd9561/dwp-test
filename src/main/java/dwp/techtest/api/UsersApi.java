package dwp.techtest.api;

import dwp.techtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.util.List;

@Component
public class UsersApi {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dwp.user.api.baseurl}")
    private String baseUrl;

    public List<User> getUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<User>> schemaType = new ParameterizedTypeReference<List<User>>() {
        };
        String userServiceEndpoint = baseUrl+Endpoints.USERS_ENDPOINT;

        List<User> users = null;
        try {
            ResponseEntity<List<User>> res =
                    restTemplate.exchange(userServiceEndpoint, HttpMethod.GET, entity, schemaType);
            users = res.getBody();
            return users;
        } catch (RestClientResponseException exception) {
            throw exception;
        }

    }

    public List<User> getUserByCity(String city) {
        HttpHeaders httpHeaders = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<User>> schemaType = new ParameterizedTypeReference<List<User>>() {
        };
        String cityUsersEndpoint = baseUrl+Endpoints.CITY_USERS_ENDPOINT.replace("{city}", city);
        List<User> users = null;
        try {
            ResponseEntity<List<User>> res =
                    restTemplate.exchange(cityUsersEndpoint, HttpMethod.GET, entity, schemaType);
            users = res.getBody();
            return users;
        } catch (RestClientResponseException exception) {
            throw exception;
        }

    }

}
