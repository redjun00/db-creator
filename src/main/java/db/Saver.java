package db;

import domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Saver {

    @Autowired
    public City save(CityRepository cityRepository, City city){

        City savedEntity = cityRepository.save(city);
        return savedEntity;
    }
}
