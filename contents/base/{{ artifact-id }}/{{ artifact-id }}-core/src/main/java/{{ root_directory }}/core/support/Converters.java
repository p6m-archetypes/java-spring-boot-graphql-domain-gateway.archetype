package {{ root_package }}.core.support;

{% for service_key in services -%}
{% set service = services[service_key] %}
{%- for entity_key in service.model.entities -%}
{%- set entity = service.model.entities[entity_key] %}
{% if use-default-service == false %}import {{ service.root_package }}.grpc.v1.{{ entity_key | pascal_case }}Dto;{% endif %}
import {{ root_package }}.graphql.types.{{ entity_key | pascal_case }};
{%- endfor %}
{%- endfor %}

public class Converters {

    private Converters() {
    }
{% if use-default-service == false %}
{%- for service_key in services -%}
{% set service = services[service_key] %}
{%- for entity_key in service.model.entities -%}
{% set entity = service.model.entities[entity_key] %}

    public static {{ entity_key | pascal_case }} to{{ entity_key | pascal_case }}({{ entity_key | pascal_case }}Dto {{ entity_key | camel_case }}Dto) {
        return {{ entity_key | pascal_case }}.newBuilder()
                        .id({{ entity_key | camel_case }}Dto.getId()
                                   .getValue())
                        .name({{ entity_key | camel_case }}Dto.getName())
                        .build();
    }
{%- endfor %}
{%- endfor %}
{% endif %}

}
