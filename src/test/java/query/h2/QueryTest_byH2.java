package query.h2;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import hello.Application;
import hello.domain.Customer;
import hello.query.CustomerQueryer;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)//또는 SpringJUnit4ClassRunner.class
@ContextConfiguration(classes = {Application.class, SpringTestJpaConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
    DbTestExecutionListener.class
})
@Transactional
public class QueryTest_byH2 {

    @Autowired
    private CustomerQueryer customerQueryer;

    @Before
    public void before(){//h2 db setting

//        new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("db/sql/customer_insert.sql")
//                .build();

    }

    @Test
    @DatabaseSetup("db/sql/customer_insert.xml")//?????왜 안먹지..
    public void test_by_h2(){

        List<Customer> customers = customerQueryer.getCustomers();
        log.debug("customers=" + customers.toString());

        assertEquals("3개를 넣었는데 3개가 아니다!", 3, customers.size());

        Customer firstCustomer = customers.get(0);
        customerQueryer.deleteCustomerById(firstCustomer.getId());

        assertEquals("1개를 지웠는데 2개가 아니다!", 2, customerQueryer.getCustomers().size());
    }

    @Test
    public void 테스트_할_때_마다_db_세팅이_다시_되는지(){

        List<Customer> customers = customerQueryer.getCustomers();
        log.debug("2 test customers=" + customers.toString());

        assertEquals("3개를 넣었는데 3개가 아니다!", 3, customers.size());
    }
}
