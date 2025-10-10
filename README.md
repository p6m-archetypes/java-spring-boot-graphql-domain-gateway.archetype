# Java Spring Boot GraphQL Domain Gateway Archetype

![Latest Release](https://img.shields.io/github/v/release/p6m-archetypes/java-spring-boot-graphql-domain-gateway.archetype?style=flat-square&label=Latest%20Release&color=blue)

Production-ready archetype for generating Spring Boot GraphQL federation gateways that aggregate multiple backend services into a unified GraphQL API for client consumption.

## 🎯 What This Generates

This archetype creates a complete, production-ready GraphQL domain gateway with:

- **🌐 GraphQL Federation**: Unified GraphQL API aggregating multiple backend services
- **⚡ Spring Boot Stack**: Spring Boot 3+ with Spring for GraphQL
- **🔌 Service Aggregation**: Connect and compose schemas from multiple gRPC or REST backend services
- **🎨 Schema Stitching**: Combine multiple GraphQL schemas into a single, cohesive API
- **🚀 Backend-for-Frontend (BFF)**: Optimized API layer tailored for client applications
- **📊 Built-in Monitoring**: Spring Boot Actuator with health checks and metrics
- **🐳 Container-Ready**: Docker and Kubernetes deployment manifests
- **🧪 Comprehensive Testing**: Unit and integration tests for gateway logic
- **🔧 Local Development**: Tilt integration for Kubernetes development
- **📈 Performance Optimized**: DataLoader integration for efficient batch queries

## 📦 Generated Project Structure

```
my-domain-gateway/
├── src/
│   ├── main/
│   │   ├── java/com/example/gateway/
│   │   │   ├── config/        # Spring and GraphQL configuration
│   │   │   ├── datafetchers/  # GraphQL data fetchers (resolvers)
│   │   │   ├── model/         # GraphQL types and DTOs
│   │   │   ├── service/       # Backend service clients
│   │   │   └── gateway/       # Gateway orchestration logic
│   │   ├── resources/
│   │   │   ├── graphql/       # GraphQL schema definitions (.graphqls)
│   │   │   └── application.yml
│   └── test/
│       ├── java/              # Unit and integration tests
│       └── resources/
├── k8s/                        # Kubernetes manifests
├── .gitlab-ci.yml             # GitLab CI/CD pipeline
├── Dockerfile
├── Tiltfile
└── pom.xml                    # Maven build configuration
```

## 🚀 Quick Start

### Prerequisites

- [Archetect](https://archetect.github.io/) CLI tool
- Java 17+ (Java 21 recommended)
- Maven 3.8+
- Docker Desktop (for containerized development)

### Generate a New Gateway

```bash
# Using SSH
archetect render git@github.com:p6m-archetypes/java-spring-boot-graphql-domain-gateway.archetype.git

# Using HTTPS
archetect render https://github.com/p6m-archetypes/java-spring-boot-graphql-domain-gateway.archetype.git

# Example prompt answers:
# project: Shopping
# group-prefix: com.example
# team-name: Platform
```

### Development Workflow

```bash
cd shopping-domain-gateway

# 1. Build the project
mvn clean install

# 2. Run tests
mvn test

# 3. Start the gateway
mvn spring-boot:run

# 4. Access endpoints
# - GraphQL API: http://localhost:9000/graphql
# - GraphiQL IDE: http://localhost:9000/graphiql
# - GraphQL Schema: http://localhost:9000/graphql/schema
# - Health Check: http://localhost:9001/actuator/health
# - Metrics: http://localhost:9001/actuator/metrics
# - All Actuator Endpoints: http://localhost:9001/actuator
```

## 📋 Configuration Prompts

When rendering the archetype, you'll be prompted for the following values:

| Property | Description | Example | Required |
|----------|-------------|---------|----------|
| `project` | Domain name for the gateway (e.g., Shopping, Platform) | Shopping | Yes |
| `group-prefix` | Maven group ID prefix (reverse domain notation) | com.example | Yes |
| `team-name` | Owning team identifier for artifacts and documentation | Platform | Yes |

**Derived Properties:**
- `service-port`: Set to 9000 for GraphQL HTTP traffic
- `management-port`: Set to 9001 for Spring Boot Actuator endpoints
- `debug-port`: Set to 9009 for remote debugging

**Optional Configuration:**
- `services`: Configuration for backend services to aggregate (defined in answers file or interactively)

For complete property relationships, see [archetype.yaml](./archetype.yaml).

## ✨ Key Features

### 🏛️ Architecture & Design

- **API Gateway Pattern**: Single entry point for multiple backend services
- **Backend-for-Frontend (BFF)**: Tailored API optimized for specific client needs
- **Schema Federation**: Compose multiple service schemas into unified API
- **Domain-Driven Design**: Organize gateway by business domains
- **Separation of Concerns**: Clear boundaries between gateway logic and service clients

### 🔧 Technology Stack

- **Spring Boot 3+**: Enterprise-grade framework with auto-configuration
- **Spring for GraphQL**: Official Spring GraphQL integration
- **GraphQL Java**: Robust GraphQL implementation for Java
- **DataLoader**: Efficient batching and caching for N+1 query prevention
- **Maven**: Dependency management and build automation
- **gRPC/REST Clients**: Connect to backend services via gRPC or REST
- **Resilience4j**: Circuit breakers and retry logic for fault tolerance
- **Tilt**: Local Kubernetes development with hot reload

### 📊 Observability & Monitoring

- **Spring Boot Actuator**: Production-ready monitoring endpoints
- **Health Checks**: Liveness and readiness endpoints for Kubernetes probes
- **GraphQL Metrics**: Query performance and execution tracking
- **Service Health**: Aggregate health from backend services
- **Metrics**: Micrometer metrics with Prometheus format support
- **Distributed Tracing**: OpenTelemetry support for request tracing

### 🧪 Testing & Quality

- **Unit Tests**: JUnit 5 for business logic validation
- **Integration Tests**: End-to-end GraphQL query testing
- **Schema Validation**: Automated schema validation and compatibility checks
- **Mock Services**: Test with mocked backend service responses
- **GraphQL Testing**: Query and mutation testing with test harness

### 🚢 DevOps & Deployment

- **Docker**: Multi-stage Dockerfile for optimized production images
- **Kubernetes**: Complete deployment manifests with ConfigMaps and Secrets
- **GitLab CI**: Pre-configured CI/CD pipeline for build, test, and deploy
- **Tilt**: Hot-reload development in local Kubernetes clusters
- **Artifactory**: Docker image publication configuration
- **Health Probes**: Kubernetes liveness and readiness probe configuration

## 🎯 Use Cases

This archetype is ideal for:

1. **API Aggregation**: Combining multiple backend microservices into a unified GraphQL API
   - Federate gRPC services (products, users, inventory, orders)
   - Integrate REST APIs (legacy services, third-party APIs)
   - Compose multiple GraphQL services (schema stitching/federation)
   - Single endpoint for frontend applications to consume

2. **Backend-for-Frontend (BFF)**: Creating client-specific APIs for different platforms
   - Web BFF with desktop-optimized queries
   - Mobile BFF with reduced payload sizes
   - Desktop BFF with rich data models
   - Each BFF tailored to specific client needs and network constraints

3. **GraphQL Federation Hub**: Organizing distributed GraphQL schemas
   - Multiple GraphQL domain services (products, users, orders)
   - Unified schema with cross-domain relationships
   - Apollo Federation or schema stitching patterns
   - Gradual migration from monolithic to federated GraphQL

4. **Microservices Facade**: Simplifying complex service meshes for client consumption
   - Hide internal service complexity from frontend teams
   - Handle service orchestration and data composition
   - Provide consistent error handling and response formats
   - Circuit breakers and fallbacks for service failures

5. **Transport Translation**: Converting between different API styles
   - gRPC (internal) → GraphQL (frontend)
   - REST (legacy) → GraphQL (modern)
   - Multiple transports → Unified GraphQL interface
   - Gradual migration path from REST to GraphQL

**Typical Architecture**:
```
Frontend Apps (React, Vue, Angular)
        ↓
GraphQL Domain Gateway (this archetype)
        ↓
Internal Services:
  - gRPC Services (high-performance)
  - REST Services (legacy/third-party)
  - GraphQL Services (domain-specific)
  - Databases (when needed)
```

## 📚 What's Inside

### Core Components

#### GraphQL Schema
Unified GraphQL schema composed from multiple backend services. Defines queries, mutations, and types available to clients.

#### Data Fetchers (Resolvers)
Spring components that implement GraphQL resolvers. Handle field resolution, data fetching from backend services, and error handling.

#### Service Clients
HTTP or gRPC clients for communicating with backend microservices. Include connection pooling, timeout configuration, and circuit breakers.

#### DataLoader Integration
Batching and caching layer to prevent N+1 query problems when fetching related data from multiple services.

#### Spring Boot Actuator
Comprehensive monitoring endpoints including health checks, metrics, environment info, and custom gateway metrics.

### Development Tools

- **GraphiQL IDE**: Interactive GraphQL query editor and documentation explorer
- **Schema Introspection**: Automatic schema documentation and validation
- **Tilt Configuration**: Auto-reload development in Kubernetes
- **Maven Wrapper**: Consistent Maven version across environments

### Configuration Management

- **application.yml**: Spring Boot configuration with profiles for dev, test, and prod
- **Service Configuration**: Backend service endpoints and connection settings
- **GraphQL Configuration**: Query complexity limits, execution timeouts, and caching
- **Resilience Configuration**: Circuit breaker, retry, and timeout policies

## 🔧 GraphQL-Specific Features

### Schema & Type System

- **Schema-First Development**: Define schemas in `.graphqls` files
- **Type Safety**: Strong typing with Java class mapping
- **Custom Scalars**: Support for DateTime, UUID, and custom scalar types
- **Directives**: Custom directives for authorization, caching, and field resolution

### Query Capabilities

- **Field Selection**: Clients request only the fields they need
- **Nested Queries**: Traverse relationships across multiple services
- **Fragments**: Reusable query fragments for complex queries
- **Aliases**: Query the same field multiple times with different arguments
- **Variables**: Parameterized queries for dynamic inputs

### Advanced Features

- **DataLoader**: Automatic request batching and caching
- **Query Complexity Analysis**: Prevent expensive queries from overloading backend services
- **Depth Limiting**: Configurable query depth limits
- **Pagination**: Cursor-based and offset pagination support
- **Subscriptions**: Real-time updates via WebSocket (optional)
- **Federation**: GraphQL Federation 2.0 support for distributed schemas

### Performance & Optimization

- **Field-Level Caching**: Cache individual field resolutions
- **Request Batching**: Combine multiple backend service calls
- **Connection Pooling**: Reuse HTTP/gRPC connections to backend services
- **Circuit Breakers**: Prevent cascading failures from backend services
- **Async Execution**: Non-blocking request handling

## 📋 Validation & Quality Assurance

Generated gateways are production-ready and include:

- ✅ Successful Maven build with all dependencies resolved
- ✅ All unit and integration tests pass
- ✅ Docker image builds successfully
- ✅ Service starts and responds to health checks
- ✅ GraphQL schema is valid and queryable
- ✅ Backend service connections configured
- ✅ GraphiQL interface accessible

Validate your generated gateway:

```bash
mvn clean verify
docker build -t my-gateway .
```

### Manual Testing with GraphiQL

Access the GraphiQL IDE at `http://localhost:9000/graphiql` to:
- Explore the schema with auto-complete
- Run test queries and mutations
- View schema documentation
- Debug query execution

Example query:

```graphql
query GetProduct {
  product(id: "123") {
    id
    name
    price
    inventory {
      quantity
      warehouse
    }
  }
}
```

### Testing with curl

```bash
# Execute GraphQL query
curl http://localhost:9000/graphql \
  -H "Content-Type: application/json" \
  -d '{"query": "{ product(id: \"123\") { id name } }"}'

# Check health
curl http://localhost:9001/actuator/health

# View GraphQL metrics
curl http://localhost:9001/actuator/metrics/graphql.query
```

## 🛠️ Advanced Features

### Configuring Backend Services

Define backend services in `application.yml`:

```yaml
services:
  product-service:
    url: http://product-service:9010
    type: grpc
    timeout: 5s
  inventory-service:
    url: http://inventory-service:8080
    type: rest
    timeout: 3s
```

### Circuit Breaker Configuration

Configure resilience patterns:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      product-service:
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10s
```

### Query Complexity Limits

Prevent resource exhaustion:

```yaml
graphql:
  query:
    max-complexity: 200
    max-depth: 10
```

### Maven Profiles

Switch between environments:

```bash
# Development profile
mvn spring-boot:run -Pdev

# Production profile
mvn spring-boot:run -Pprod
```

## 🔗 Related Archetypes

- **[Java Spring Boot gRPC Service](../java-spring-boot-grpc-service.archetype)** - Backend gRPC service for this gateway to aggregate
- **[.NET GraphQL Service](../dotnet-graphql-service-basic.archetype)** - .NET GraphQL service alternative
- **[Python GraphQL Service](../python-graphql-service-uv-basic.archetype)** - Python GraphQL service for comparison
- **[TypeScript Next.js](../typescript-nextjs-basic.archetype)** - Frontend application that can consume this gateway

## 🤝 Contributing

This archetype is actively maintained. For issues or enhancements:

1. Check existing issues in the repository
2. Create detailed bug reports or feature requests
3. Follow the contribution guidelines
4. Test changes with Maven verify
5. Validate schema federation scenarios

## 📄 License

This archetype is released under the MIT License. Generated gateways inherit this license but can be changed as needed for your organization.

---

**Ready to build production-grade GraphQL gateways with Spring Boot?** Generate your first gateway and start aggregating services in minutes! 🚀
