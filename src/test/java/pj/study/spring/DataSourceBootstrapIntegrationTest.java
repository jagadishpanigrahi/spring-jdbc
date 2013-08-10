package pj.study.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/applicationContext.xml")
public class DataSourceBootstrapIntegrationTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void test() {
       assertNotNull("dataSource cannot be null", dataSource);
    }
}
