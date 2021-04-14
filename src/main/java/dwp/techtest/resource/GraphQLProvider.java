package dwp.techtest.resource;

import com.coxautodev.graphql.tools.SchemaParser;
import dwp.techtest.api.UsersApi;
import dwp.techtest.queries.UserQuery;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLProvider {
    @Autowired
    private UsersApi usersApi;


    @Bean
    public GraphQLSchema schema (){
        return SchemaParser.newParser()
                .file("graphQLS/user.graphqls")
                .resolvers(
                        new UserQuery(usersApi)
                )
                .build()
                .makeExecutableSchema();

    }



}
