package {{ root_package }}.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import {{ root_package }}.core.{{ ProjectPrefix }}{{ ProjectSuffix }}CoreConfig;
import {{ root_package }}.server.exception.GraphqlExceptionHandler;

@SpringBootApplication(exclude = {
        LiquibaseAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@Import({
        {{ ProjectPrefix }}{{ ProjectSuffix }}CoreConfig.class
})
public class {{ ProjectPrefix }}{{ ProjectSuffix }}ServerConfig {

    @Bean
    public GraphqlExceptionHandler graphqlExceptionHandler() {
        return new GraphqlExceptionHandler();
    }
}
