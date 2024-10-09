package {{ root_package }}.server.graphql;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

@Component
public class FetcherExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if(handlerParameters.getException() instanceof DgsEntityNotFoundException) {
            GraphQLError graphqlError = TypedGraphQLError.newNotFoundBuilder().message("Requested entity not found")
                    .path(handlerParameters.getPath()).build();
            return DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();
        }

        if(handlerParameters.getException() instanceof IllegalArgumentException){
            GraphQLError graphqlError = TypedGraphQLError.newBadRequestBuilder().message(handlerParameters.getException().getMessage())
                    .path(handlerParameters.getPath()).build();
            return DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();
        }

        return new DefaultDataFetcherExceptionHandler().onException(handlerParameters);
    }
}