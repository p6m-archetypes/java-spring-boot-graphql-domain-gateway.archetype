package {{root_package}}.integration.tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import {{ root_package }}.server.{{ ProjectPrefix }}{{ ProjectSuffix }}Server;

@Configuration
public class IntegrationTestsConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public {{ ProjectPrefix }}{{ ProjectSuffix }}Server {{ projectPrefix }}{{ ProjectSuffix }}Server() {
        return new {{ ProjectPrefix }}{{ ProjectSuffix }}Server()
                .withRandomPorts()
                ;
    }

    @Bean
    public DgsQueryExecutor queryExecutor({{ ProjectPrefix }}{{ ProjectSuffix }}Server server) {
        return server.getContext().getBean(DgsQueryExecutor.class);
    }
}
