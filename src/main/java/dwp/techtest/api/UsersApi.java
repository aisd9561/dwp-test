package dwp.techtest.api;

import dwp.techtest.model.User;
import dwp.techtest.util.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class UsersApi {

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<List<User>> getUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<User>> schemaType = new ParameterizedTypeReference<List<User>>() {
        };
        String userServiceEndpoint = Endpoints.USERS_ENDPOINT;

        List<User> users = null;
        try {
            ResponseEntity<List<User>> res =
                    restTemplate.exchange(userServiceEndpoint, HttpMethod.GET, entity, schemaType);
            users = res.getBody();
            return CompletableFuture.completedFuture(users);
        } catch (RestClientException exception) {
            throw exception;
        }

    }

    public CompletableFuture<List<User>> getUserByCity(String city) {
        HttpHeaders httpHeaders = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<User>> schemaType = new ParameterizedTypeReference<List<User>>() {
        };
        String cityUsersEndpoint = Endpoints.CITY_USERS_ENDPOINT.replace("{city}", city);
        List<User> users = null;
        try {
            ResponseEntity<List<User>> res =
                    restTemplate.exchange(cityUsersEndpoint, HttpMethod.GET, entity, schemaType);
            users = res.getBody();
            return CompletableFuture.completedFuture(users);
        } catch (RestClientException exception) {
            throw exception;
        }

    }

}
