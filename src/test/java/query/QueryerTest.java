package query;

import hello.Application;
import hello.domain.Customer;
import hello.query.Queryer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {Application.class}, initializers = ConfigFileApplicationContextInitializer.class)   //context configuration 가져오는 어노테이션 더 알아보자.
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Queryer queryer;

    @Before
    public void before(){
        assertNotNull(applicationContext);
        assertNotNull(queryer);
    }

    @Test
    public void success_getCustomers(){

        List<Customer> actual = queryer.getCustomers();
        System.out.println("** actual=" + actual);
        assertNotNull(actual);
    }

    @Test
    public void success_deleteCustomerById(){
        List<Customer> baseCustomers = queryer.getCustomers();
        assertNotNull(baseCustomers);
        int originalSize = baseCustomers.size();
        assertTrue(originalSize > 0);

        Customer expectedCustomer = baseCustomers.get(0);
        assertNotNull(expectedCustomer);

        //test
        long expectedId = expectedCustomer.getId();
        queryer.deleteCustomerById(expectedId);

        //assert
        List<Customer> customers = queryer.getCustomers();
        assertFalse(customers.contains(expectedCustomer));

        Customer actualCustomers = queryer.getCustomerById(expectedId);
        assertNull(actualCustomers);
    }

}
