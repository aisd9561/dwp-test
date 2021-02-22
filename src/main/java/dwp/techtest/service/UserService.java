package dwp.techtest.service;

import dwp.techtest.model.Location;
import dwp.techtest.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsersFromLocation(String city, Location location, int maxDistance);
}
