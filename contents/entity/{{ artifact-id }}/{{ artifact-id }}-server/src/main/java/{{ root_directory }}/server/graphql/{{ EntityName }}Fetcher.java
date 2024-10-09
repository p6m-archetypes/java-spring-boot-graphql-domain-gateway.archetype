package {{ root_package }}.server.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import {{ root_package }}.core.{{ ProjectPrefix }}DomainGatewayCore;
import {{ root_package }}.graphql.types.{{ EntityName }};

import java.util.List;

@DgsComponent
public class {{ EntityName }}Fetcher {

    private final {{ ProjectPrefix }}DomainGatewayCore domainGatewayCore;

    public {{ EntityName }}Fetcher({{ ProjectPrefix }}DomainGatewayCore domainGatewayCore) {
        this.domainGatewayCore = domainGatewayCore;
    }

    @DgsQuery
    public List<{{ EntityName }}> {{ entityName | pluralize }}() {
        return domainGatewayCore.{{ entityName | pluralize }}();
    }

    @DgsQuery
    public {{ EntityName }} {{ entityName }}(@InputArgument String id) {
        return domainGatewayCore.{{ entityName }}(id);
    }
}
