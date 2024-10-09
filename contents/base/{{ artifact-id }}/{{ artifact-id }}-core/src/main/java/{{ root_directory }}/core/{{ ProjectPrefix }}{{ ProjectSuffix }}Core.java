package {{ root_package }}.core;

import {{ root_package }}.core.support.Converters;
{%- for service_key in services -%}
{% set service = services[service_key] %}
import {{ service.root_package }}.api.v1.{{ service['ProjectName'] }};
{%- for entity_key in service.model.entities -%}
{%- set entity = service.model.entities[entity_key] %}
import {{ service.root_package }}.grpc.v1.Get{{ entity_key | pascal_case }}Request;
import {{ service.root_package }}.grpc.v1.Get{{ entity_key | pascal_case }}Response;
import {{ service.root_package }}.grpc.v1.Get{{ entity_key | pascal_case | pluralize }}Request;
import {{ service.root_package }}.grpc.v1.Get{{ entity_key | pascal_case | pluralize }}Response;
import {{ root_package }}.graphql.types.{{ entity_key | pascal_case }};
import {{ root_package }}.graphql.types.Create{{ entity_key | pascal_case }}Input;
import {{ root_package }}.graphql.types.Update{{ entity_key | pascal_case }}Input;
{%- endfor %}
{%- endfor %}
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class {{ ProjectPrefix }}{{ ProjectSuffix }}Core {{'{'}}
    {% for service_key in services %}
    {% set service = services[service_key] -%}
    final {{ service['ProjectName'] }} {{ service['projectName'] }};
    {%- endfor %}

    public {{ ProjectPrefix }}{{ ProjectSuffix }}Core({%- for service_key in services %}
    {%- set service = services[service_key] %}
        final {{ service['ProjectName'] }} {{ service['projectName'] }}{% if not loop.last %},{% endif %}
    {%- endfor %}
    ) {{'{'}}{%- for service_key in services %}
        {% set service = services[service_key] -%}
        this.{{ service['projectName'] }} = {{ service['projectName'] }};
        {%- endfor %}
    }

{%- for service_key in services -%}
{%- set service = services[service_key] -%}
{%- for entity_key in service.model.entities -%}
{%- set entity = service.model.entities[entity_key] %}

    public {{ entity_key | pascal_case }} {{ entity_key | camel_case }}(String id) {
        Get{{ entity_key | pascal_case }}Response response = {{ entity_key | camel_case }}Service.get{{ entity_key | pascal_case }}(Get{{ entity_key | pascal_case }}Request.newBuilder()
                                                             .setId(id)
                                                             .build());
        return Converters.to{{ entity_key | pascal_case }}(response.get{{ entity_key | pascal_case }}());
    }

    public List<{{ entity_key | pascal_case }}> {{ entity_key | camel_case | pluralize }}() {
        Get{{ entity_key | pascal_case | pluralize }}Request request = Get{{ entity_key | pascal_case | pluralize }}Request.newBuilder()
                                                 .setStartPage(0)
                                                 .setPageSize(10)
                                                 .build();
        Get{{ entity_key | pascal_case | pluralize }}Response response = {{ entity_key | camel_case }}Service.get{{ entity_key | pascal_case | pluralize }}(request);
        return response.get{{ entity_key | pascal_case }}List()
                                   .stream()
                                   .map(Converters::to{{ entity_key | pascal_case }})
                                   .collect(Collectors.toList());
    }

    public {{ entity_key | pascal_case }} create{{ entity_key | pascal_case }}(Create{{ entity_key | pascal_case}}Input {{ entity_key | camel_case }}) {
        return null;
    }

    public {{ entity_key | pascal_case }} update{{ entity_key | pascal_case }}(Update{{ entity_key | pascal_case}}Input {{ entity_key | camel_case }}) {
        return null;
    }
{%- endfor %}
{%- endfor %}
}
