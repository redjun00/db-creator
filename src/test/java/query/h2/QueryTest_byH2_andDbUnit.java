package query.h2;

import hello.Application;
import hello.domain.Customer;
import hello.query.CustomerQueryer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * H2와 DbUnit을 사용하여 작성하도록한 테스트 케이스.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, SpringTestJpaConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbTestExecutionListener.class       //여기에서 데이터를 세팅한다.
})
@Transactional
public class QueryTest_byH2_andDbUnit {

    @Autowired
    private CustomerQueryer customerQueryer;

    @Test
    public void success_getCustomers() {
        List<Customer> customers = customerQueryer.getCustomers();
        log.debug("customers=" + customers.toString());

        assertEquals("3개를 넣었는데 3개가 아니다!", 3, customers.size());
    }

    @Test
    public void 테스트_할_때_마다_db_세팅이_다시_되는지(){

        List<Customer> customers = customerQueryer.getCustomers();
        log.debug("customers=" + customers.toString());

        assertEquals("3개를 넣었는데 3개가 아니다!", 3, customers.size());

        Customer firstCustomer = customers.get(0);
        customerQueryer.deleteCustomerById(firstCustomer.getId());

        assertEquals("1개를 지웠는데 2개가 아니다!", 2, customerQueryer.getCustomers().size());
    }
}
