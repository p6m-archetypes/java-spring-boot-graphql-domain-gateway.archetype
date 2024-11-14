package {{ root_package }}.integration.tests;

import com.google.protobuf.StringValue;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import {{ root_package }}.graphql.client.*;
import {{ root_package }}.graphql.types.*;
import {{ service.root_package }}.grpc.v1.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.grpcmock.GrpcMock.*;
import static {{ service.root_package }}.grpc.v1.{{ service.ProjectName}}Grpc.*;

public class {{ ProjectName }}IT extends {{ ProjectName }}BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final TypeRef<List<{{ EntityName }}>> {{ ENTITY_NAME }}_LIST_TYPEREF = new TypeRef<>() {
    };

    private final String id   = UUID.randomUUID()
                              .toString();
    private final String name = "name_" + UUID.randomUUID();


    @Test
//    @WithJWTUser(userId = USER_ID_STR, identityProvider = IdentityProvider.AUTH0, identityProviderId = "111")
    void test_Get{{ EntityName }}ById() {
        stubGet{{ EntityName }}();
        String query = new GraphQLQueryRequest(
            {{ EntityName }}GraphQLQuery.newRequest()
                               .id(id)
                               .build(),
            new {{ EntityName }}ProjectionRoot()
                .id()
                .name()
        ).serialize();

        logger.info(query);

        {{ EntityName }} {{ entityName }} = queryExecutor.executeAndExtractJsonPathAsObject(query,
            "data" + ".{{ entityName }}", {{ EntityName }}.class);

        assertThat({{ entityName }}.getId()).isEqualTo(id);
        assertThat({{ entityName }}.getName()).isEqualTo(name);
    }

    @Test
    void test_Get{{ EntityName | pluralize }}() {
        stubGet{{ EntityName | pluralize }}();
        String query = new GraphQLQueryRequest(
            {{ EntityName | pluralize }}GraphQLQuery.newRequest()
                               .build(),
            new {{ EntityName }}ProjectionRoot()
                .id()
                .name()
        ).serialize();

        logger.info(query);

        List<{{ EntityName }}> {{ entityName | pluralize }} = queryExecutor.executeAndExtractJsonPathAsObject(query,
            "data" + ".{{ entityName | pluralize }}", {{ ENTITY_NAME }}_LIST_TYPEREF);

        assertThat({{ entityName | pluralize }}).hasSize(3);
    }

    @Test
    void test_Create{{ EntityName }}() {
        stubCreate{{ EntityName }}();
        String query = new GraphQLQueryRequest(
            Create{{ EntityName }}GraphQLQuery.newRequest()
                                     .create{{ EntityName }}Input(Create{{ EntityName }}Input.newBuilder()
                                                                           .name(name)
                                                                           .build())
                                     .build(),
            new {{ EntityName }}ProjectionRoot()
                .id()
                .name()
        ).serialize();

        logger.info(query);

        {{ EntityName }} {{ entityName }} = queryExecutor.executeAndExtractJsonPathAsObject(query,
            "data" + ".create{{ EntityName }}", {{ EntityName }}.class);

        assertThat({{ entityName }}.getId()).isEqualTo(id);
        assertThat({{ entityName }}.getName()).isEqualTo(name);

        verifyThat(
            calledMethod(getCreate{{ EntityName }}Method())
                .withStatusOk()
                .withRequest({{ EntityName }}Dto.newBuilder()
                                       .setName(name)
                                       .build()));
    }

    @Test
    void test_Update{{ EntityName }}() {
        stubUpdate{{ EntityName }}();
        String query = new GraphQLQueryRequest(
            Update{{ EntityName }}GraphQLQuery.newRequest()
                                     .update{{ EntityName }}Input(Update{{ EntityName }}Input.newBuilder()
                                                                           .targetId(id)
                                                                           .name(name + "_updated")
                                                                           .build())
                                     .build(),
            new {{ EntityName }}ProjectionRoot()
                .id()
                .name()
        ).serialize();

        logger.info(query);

        {{ EntityName }} {{ entityName }} = queryExecutor.executeAndExtractJsonPathAsObject(query,
            "data" + ".update{{ EntityName }}", {{ EntityName }}.class);

        assertThat({{ entityName }}.getId()).isEqualTo(id);
        assertThat({{ entityName }}.getName()).isEqualTo(name + "_updated");

        verifyThat(
            calledMethod(getUpdate{{ EntityName }}Method())
                .withStatusOk()
                .withRequest({{ EntityName }}Dto.newBuilder()
                                       .setId(StringValue.of(id))
                                       .setName(name + "_updated")
                                       .build()));
    }

    @Test
    void test_Delete{{ EntityName }}() {
        stubDelete{{ EntityName }}();
        String query = new GraphQLQueryRequest(
            Delete{{ EntityName }}GraphQLQuery.newRequest()
                                     .id(id)
                                     .build()).serialize();

        logger.info(query);

        Boolean response = queryExecutor.executeAndExtractJsonPathAsObject(query,
            "data" + ".delete{{ EntityName }}", Boolean.class);

        assertThat(response).isTrue();

        verifyThat(
            calledMethod(getDelete{{ EntityName }}Method())
                .withStatusOk()
                .withRequest(Delete{{ EntityName }}Request.newBuilder()
                                                 .setId(id)
                                                 .build()));
    }

    private void stubDelete{{ EntityName }}() {
        stubFor(unaryMethod(getDelete{{ EntityName }}Method()).willReturn(
            Delete{{ EntityName }}Response.newBuilder()
                                 .setMessage("Success")
                                 .build()
        ));

    }

    protected void stubGet{{ EntityName | pluralize }}() {
        stubFor(unaryMethod(getGet{{ EntityName | pluralize }}Method()).willReturn(
            Get{{ EntityName | pluralize }}Response.newBuilder()
                               .addAll{{ EntityName }}(List.of(
                                   {{ entityName }}DtoBuilder().setId(StringValue.of(UUID.randomUUID()
                                                                                .toString()))
                                                      .build(),
                                   {{ entityName }}DtoBuilder().setId(StringValue.of(UUID.randomUUID()
                                                                                .toString()))
                                                      .build(),
                                   {{ entityName }}DtoBuilder().setId(StringValue.of(UUID.randomUUID()
                                                                                .toString()))
                                                      .build()
                               ))
                               .build()
        ));
    }

    protected void stubGet{{ EntityName }}() {
        stubFor(unaryMethod(getGet{{ EntityName }}Method()).willReturn(
            Get{{ EntityName }}Response.newBuilder()
                              .set{{ EntityName }}(
                                  {{ entityName }}DtoBuilder().build()
                              )
                              .build()
        ));
    }

    protected void stubCreate{{ EntityName }}() {
        stubFor(unaryMethod(getCreate{{ EntityName }}Method()).willReturn(
            Create{{ EntityName }}Response.newBuilder()
                                 .set{{ EntityName }}(
                                     {{ entityName }}DtoBuilder().build()
                                 )
                                 .build()
        ));
    }

    protected void stubUpdate{{ EntityName }}() {
        stubFor(unaryMethod(getUpdate{{ EntityName }}Method()).willReturn(
            Update{{ EntityName }}Response.newBuilder()
                                 .set{{ EntityName }}(
                                     {{ entityName }}DtoBuilder().setName(name + "_updated")
                                                        .build()
                                 )
                                 .build()
        ));
    }

    protected {{ EntityName }}Dto.Builder {{ entityName }}DtoBuilder() {
        return {{ EntityName }}Dto.newBuilder()
                         .setId(StringValue.of(id))
                         .setName(name);
    }
}
