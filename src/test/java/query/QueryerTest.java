package query;

import hello.Application;
import hello.config.DataBaseConfig;
import hello.domain.Cue;
import hello.query.Queryer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

//@SpringApplicationConfiguration(classes = Application.class, initializers = ConfigFileApplicationContextInitializer.class)
@ContextConfiguration(classes = {Application.class, DataBaseConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)   //context configuration 가져오는 어노테이션 더 알아보자.
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Queryer queryer;

    @Test
    public void success(){
        assertNotNull(applicationContext);

//        Queryer queryer = applicationContext.getBean(Queryer.class);
        assertNotNull(queryer);

        List<Cue> actual = queryer.getCueTable();
        System.out.println("actual=" + actual);
    }

}
