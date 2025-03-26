package {{ root_package }}.server.exception;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.execution.ResultPath;
import io.grpc.StatusRuntimeException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class GraphqlExceptionHandler implements DataFetcherExceptionHandler {
    private final DefaultDataFetcherExceptionHandler defaultHandler =
        new DefaultDataFetcherExceptionHandler();

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        // Exception comes from gRPC
        if (handlerParameters.getException() instanceof StatusRuntimeException) {
            StatusRuntimeException statusRuntimeException =
                (StatusRuntimeException) handlerParameters.getException();
            ResultPath path = handlerParameters.getPath();

            Optional<GraphQLError> maybeGraphqlError =
                StatusRuntimeExceptionHandler.getGraphqlErrorGivenStatusRuntimeException(
                    statusRuntimeException, path);
            // check to see if successfully mapped exception
            return maybeGraphqlError
                .map(
                    graphQLError ->
                        CompletableFuture.completedFuture(DataFetcherExceptionHandlerResult.newResult().error(graphQLError).build()))
                .orElse(defaultHandler.handleException(handlerParameters));
        }
        else {
            // TODO: Handle Java exceptions that direct from DGS
            return defaultHandler.handleException(handlerParameters);
        }
    }
}

