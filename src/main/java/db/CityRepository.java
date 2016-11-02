package db;

import domain.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long>{
    List<City> findByName(String name);
}
