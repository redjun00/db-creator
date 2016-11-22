package query;

import hello.Application;
import hello.domain.Customer;
import hello.query.CustomerQueryer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {Application.class}, initializers = ConfigFileApplicationContextInitializer.class)   //context configuration 가져오는 어노테이션 더 알아보자.
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerQueryerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CustomerQueryer customerQueryer;

    @Before
    public void before(){
        assertNotNull(applicationContext);
        assertNotNull(customerQueryer);
    }

    @Test
    public void success_getCustomers(){

        List<Customer> actual = customerQueryer.getCustomers();
        System.out.println("** actual=" + actual);
        assertNotNull(actual);
    }

    @Test
    public void success_deleteCustomerById(){
        List<Customer> baseCustomers = customerQueryer.getCustomers();
        assertNotNull(baseCustomers);
        int originalSize = baseCustomers.size();
        assertTrue(originalSize > 0);

        Customer expectedCustomer = baseCustomers.get(0);
        assertNotNull(expectedCustomer);

        //test
        long expectedId = expectedCustomer.getId();
        customerQueryer.deleteCustomerById(expectedId);

        //assert
        List<Customer> customers = customerQueryer.getCustomers();
        assertFalse(customers.contains(expectedCustomer));

        Customer actualCustomers = customerQueryer.getCustomerById(expectedId);
        assertNull(actualCustomers);
    }

}
