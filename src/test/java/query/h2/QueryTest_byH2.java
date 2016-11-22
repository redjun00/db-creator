package query.h2;

import hello.Application;
import hello.domain.Customer;
import hello.query.CustomerQueryer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)//또는 SpringJUnit4ClassRunner.class
@ContextConfiguration(classes = {Application.class, SpringTestJpaConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
@Transactional
@Rollback
public class QueryTest_byH2 {

    private EmbeddedDatabase db;//이걸로 db 상황을 볼 수 있다. 물론 repository가 있긴 하지만..

    @Autowired
    private CustomerQueryer customerQueryer;

    @Before
    public void before(){
        //h2 db setting

        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/customer_insert.sql")
                .build();

//        dbTemplete = new NamedParameterJdbcTemplate(db);
    }

    @Test
    public void test_by_h2(){

        List<Customer> customers = customerQueryer.getCustomers();
        log.debug("customers=" + customers.toString());

        assertEquals("3개를 넣었는데 3개가 아니다!", 3, customers.size());
    }
}
