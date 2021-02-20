package dwp.techtest.service;


import dwp.techtest.api.UsersApi;
import dwp.techtest.model.Location;
import dwp.techtest.model.User;
import dwp.techtest.util.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersApi usersApi;

    @Autowired
    GeoLocation geoLocation;


    @Override
    public List<User> getUsersFromLocation(String city,Location location,int maxDistance) {
        List<User> usersInCity = usersApi.getUserByCity(city);
        List<User> allUsers = usersApi.getUsers();
        if(usersInCity.isEmpty() && allUsers.isEmpty() || usersInCity == null && allUsers == null){
            return null;
        }
        List<User> usersWithinDistance = getUsersWithinCity(allUsers,location,maxDistance);
        return removeDuplicates(usersInCity,usersWithinDistance);
    }

    private List<User> getUsersWithinCity(List<User> allUsers, Location city ,int maxDistance ){

        if(allUsers.isEmpty()){
            return null;
        }
        List<User> usersWithinDistance = new ArrayList<>();
        for (User user : allUsers) {
            Location userLoc = new Location( user.getLatitude(),user.getLongitude());
            System.out.println(userLoc);
            Double distance = geoLocation.getDistance(city,userLoc);
            if(distance == null){
                continue;
            }
            if(distance.doubleValue() <= maxDistance){
                usersWithinDistance.add(user);
            }
        }
        return usersWithinDistance;

    }

    private List<User> removeDuplicates(List<User> list1, List<User> list2){
        List<User> filteredList = new ArrayList<>(
                Stream.of(list1, list2)
                        .flatMap(List::stream)
                        .collect(Collectors.toMap(User::getId, d -> d, (User x, User y) -> x == null ? y : x))
                        .values());

        return filteredList;

    }


}
