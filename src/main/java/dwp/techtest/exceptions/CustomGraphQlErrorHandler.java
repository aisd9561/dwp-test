package dwp.techtest.exceptions;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomGraphQlErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> errorList = new ArrayList<>();

        for (GraphQLError error:errors){
            errorList.add(new GenericGraphQLError(error.getMessage()));
        }
        return errorList;
    }


}
