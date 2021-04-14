package dwp.techtest.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import dwp.techtest.api.UsersApi;
import dwp.techtest.model.User;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserQuery implements GraphQLQueryResolver  {


    private final UsersApi usersApi;

    @Autowired
    public UserQuery(UsersApi usersApi) {
        this.usersApi = usersApi;
    }

    public List<User> getUsers (DataFetchingEnvironment env ){
        return usersApi.getUsers();
    }

    public List<User> getUserByCity (String city,DataFetchingEnvironment env ){
        return usersApi.getUserByCity(city);
    }

}






