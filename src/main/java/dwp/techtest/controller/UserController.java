package dwp.techtest.controller;

import dwp.techtest.model.User;
import dwp.techtest.service.UserService;
import dwp.techtest.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/london")
    public List<User> getUsersInAndAroundLondon() {
        return userService.getUsersFromLocation("London", Constants.LONDON,50);
    }

}
