package {{ root_package }}.server.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class EchoDataFetcher {

    @DgsQuery
    public EchoResponse echo(@InputArgument String message) {
        return new EchoResponse(message);
    }

    @DgsMutation
    public EchoResponse echo(@InputArgument EchoInput input) {
        return new EchoResponse(input.getMessage());
    }
}
