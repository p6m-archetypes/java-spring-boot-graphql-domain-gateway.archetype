{% import "macros/java" as java %}
package {{ root_package }}.integration.tests;

import com.google.protobuf.StringValue;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import {{ root_package }}.graphql.client.*;
import {{ root_package }}.graphql.types.*;
{% if use-default-service == false %}
{% for service_key in services %}
{% set service = services[service_key] -%}
import {{ service.root_package }}.grpc.v1.*;
{%- endfor %}{% endif %}


import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.grpcmock.GrpcMock.*;
{% if use-default-service == false %}
{% for service_key in services %}
{% set service = services[service_key] -%}
import static {{ service.root_package }}.grpc.v1.{{ service.ProjectName}}Grpc.*;
{%- endfor %}{% endif %}

public class {{ ProjectName }}IT extends {{ ProjectName }}BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    {% for service_key in services %}
    {% set service = services[service_key] -%}
    {%- for entity_key in service.model.entities -%}
    {%- set entity = service.model.entities[entity_key] %}
    public static final TypeRef<List<{{ entity_key | pascal_case }}>> {{ entity_key | upper_case }}_LIST_TYPEREF = new TypeRef<>() {
    };
    {% endfor %}
    {%- endfor %}
    

    private final String id   = UUID.randomUUID()
                              .toString();
    private final String name = "name_" + UUID.randomUUID();

    {%- for service_key in services -%}
    {% set service = services[service_key] %}
    {%- for entity_key in service.model.entities -%}
    {%- set entity = service.model.entities[entity_key] %}
    {% if use-default-service == false %}
    {{ java.integration_test_methods(entity_key, service.model.entities[entity_key], service.model, service) }}
    {% endif %}
    {% endfor %}
    {% endfor %}
}
