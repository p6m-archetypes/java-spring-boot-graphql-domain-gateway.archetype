package {{ root_package }}.server;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import {{ root_package }}.core.{{ ProjectPrefix }}{{ ProjectSuffix }}Core;

@SpringBootTest(classes = { DgsAutoConfiguration.class })
public class {{ ProjectPrefix }}{{ ProjectSuffix }}ServerTest {

    @Autowired
    private DgsQueryExecutor queryExecutor;

    @Mock
    private {{ ProjectPrefix }}{{ ProjectSuffix }}Core {{ projectPrefix }}{{ ProjectSuffix }}Core;
}
