package {{ root_package }}.integration.tests;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.grpcmock.junit5.GrpcMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import {{ root_package }}.integration.tests.config.IntegrationTestsConfig;
import {{ root_package }}.server.{{ ProjectPrefix }}{{ ProjectSuffix }}Server;
{% if use-default-service == false %}
{%- for service_key in services -%}
{% set service = services[service_key] %}
import {{ service.root_package }}.client.{{ service['ProjectName'] }}Client;
import {{ service.root_package }}.grpc.v1.{{ service['ProjectName'] }}Grpc;
{%- endfor %}{% endif %}
import {{ root_package }}.integration.tests.grpc.GrpcMockClientConfigurer;


@ExtendWith({SpringExtension.class, GrpcMockExtension.class})
@ContextConfiguration(classes = { IntegrationTestsConfig.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class {{ ProjectPrefix }}{{ ProjectSuffix }}BaseTest {

    @Autowired
    private {{ ProjectPrefix }}{{ ProjectSuffix }}Server {{ projectPrefix }}{{ ProjectSuffix }};

    @Autowired
    protected DgsQueryExecutor queryExecutor;

    @BeforeEach
    public void setupClients() {
        GrpcMockClientConfigurer.configure({{ projectName }}.getContext())
        {% if use-default-service == false %}
        {%- for service_key in services -%}
        {% set service = services[service_key] %}
                                .client({{ service['ProjectName'] }}Client.class, {{ service['ProjectName'] }}Grpc.class)
        {%- endfor %}{% endif %}
        ;
    }
}
