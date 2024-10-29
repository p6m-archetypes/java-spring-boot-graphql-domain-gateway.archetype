package {{ root_package }}.server.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import {{ root_package }}.core.{{ ProjectPrefix }}DomainGatewayCore;
import {{ root_package }}.graphql.types.{{ EntityName }};
import {{ root_package }}.graphql.types.Create{{ EntityName }}Input;
import {{ root_package }}.graphql.types.Update{{ EntityName }}Input;

@DgsComponent
public class {{ EntityName }}Mutation {

    private final {{ ProjectPrefix }}DomainGatewayCore domainGatewayCore;

    public {{ EntityName }}Mutation({{ ProjectPrefix }}DomainGatewayCore domainGatewayCore) {
        this.domainGatewayCore = domainGatewayCore;
    }

    @DgsMutation
    public {{ EntityName }} create{{ EntityName }}(@InputArgument Create{{ EntityName }}Input create{{ EntityName }}Input) {
        return domainGatewayCore.create{{ EntityName }}(create{{ EntityName }}Input);
    }

    @DgsMutation
    public {{ EntityName }} update{{ EntityName }}(@InputArgument Update{{ EntityName }}Input update{{ EntityName }}Input) {
        return domainGatewayCore.update{{ EntityName }}(update{{ EntityName }}Input);
    }

    @DgsMutation
    public Boolean delete{{ EntityName }}(@InputArgument String id) {
        return domainGatewayCore.delete{{ EntityName }}(id);
    }
}