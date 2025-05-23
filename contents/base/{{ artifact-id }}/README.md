# {{ project-title }}

**// TODO:** Add description of your project's business function.

Generated from the [Java Spring Boot GraphQL Domain Gateway Archetype](https://github.com/p6m-archetypes/java-spring-boot-graphql-domain-gateway-archetype). 
[[_TOC_]]

## Prereqs
Running this service requires JDK 21+ and Maven to be configured with an Artifactory encrypted key.
For development, be sure to have Docker installed and running locally.

## Build System
This project uses [Maven](https://maven.apache.org/) as its build system. Common goals include

| Goal    | Description                                                           |
|---------|-----------------------------------------------------------------------|
| clean   | Removes previously generated build files within `target` directories  |
| package | Builds and tests the project.                                         |
| install | Installs the project to your local repository for use as a dependency |

For information on Spring-specific tasks, see the [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#?.?) docs.

This is a multi-module project. To run a goal in a particular module, use the `-f` flag. For example, we could clean and
regenerate the files from our protobuf definition like this:
```bash
$ mvn -f {{ artifact-id }}-graphql clean generate-sources
```

## Run Server
Before starting the server, you must first create the application jars:
```bash
$ mvn install
```

From the project root, run
```bash
$ mvn -f {{ artifact-id }}-server spring-boot:run
```

This server accepts connections on the following ports:
- {{ service-port }}: used for application GraphQL Service traffic.
- {{ management-port }}: used to monitor the application over HTTP (see [Actuator endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints)).

[GraphiQL](http://localhost:{{ service-port }}/graphiql) - GraphQL Playground

## Management Server

### Health Checks
You can verify things are up and running by looking at the [/health](http://localhost:{{ management-port }}/health) endpoint:
```bash
$ curl localhost:{{ management-port }}/health
```

## Runtime Switches

The following are switches/settings that can be turned on or off when starting the server to affect how it operates. These
switches are use primarily as a developer convenience.

| System Property      | Environment Variable | Function                                                                  |
|:---------------------|----------------------|:--------------------------------------------------------------------------|
| -Dlogging-structured |                      | Turns on structured (JSON) logging, and turns off the Spring Boot banner. |
| -Dlogging-pretty     |                      | Turn on pretty printing when using structured-logging.                    |

## Modules

| Directory | Description |
| --------- | ----------- |
| [{{ artifact-id }}-bom]({{ artifact-id }}-bom/README.md) | {{ project-title }} Bill of Materials. |
| [{{ artifact-id }}-core]({{ artifact-id }}-core/README.md) | Business Logic. Abstracts Persistence, defines Transaction Boundaries. Implements the API. |
| [{{ artifact-id }}-graphql]({{ artifact-id }}-graphql/README.md) | GraphQL spec. |
| [{{ artifact-id }}-integration-tests]({{ artifact-id }}-integration-tests/README.md) | Leverages the Client to test the Server and it's dependencies. |
| [{{ artifact-id }}-server]({{ artifact-id }}-server/README.md) | Transport/Protocol Host.  Wraps Core. |

## Key Dependencies

| Name                                                                                           | Scope                  | Description                                                            |
|------------------------------------------------------------------------------------------------|------------------------|------------------------------------------------------------------------|
| [GraphQL Spring Boot Starter](https://github.com/LogNet/grpc-spring-boot-starter)              | API/Remoting           | Auto-configures an embedded GraphQL server integrated with Spring Boot |
| [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html/#reference) | Persistence            | ORM.                                                                   | 
| [k6](https://k6.io/)                                                                           | Load Tests             | JavaScript-driven load testing                                         |
| [AssertJ](https://joel-costigliola.github.io/assertj/)                                         | Unit Tests             | Fluent test assertions.                                                |
| [Mockito](https://site.mockito.org/)                                                           | Unit Tests             | Provides mocking and spying functionality.                             |
| [TestContainers](https://www.testcontainers.org/)                                              | Unit Tests, Containers | Programmatic container management.                                     |

## Contributions
**// TODO:** Add description of how you would like issues to be reported and people to reach out.
