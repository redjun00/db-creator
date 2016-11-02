package db;

import domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaverTest {

    @Autowired
    private Saver saver;

    @Autowired
    private CityRepository cityReposity;

    @Test
    public void save_success(){

        assertNotNull(cityReposity);

        City city = new City("seoul", "korea");
        City savedEntity = saver.save(cityReposity, city);
        assertNotNull(savedEntity);
        assertEquals(city, savedEntity);
    }
}
