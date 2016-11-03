package hello.repository;

import hello.domain.Cue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CueRepository extends CrudRepository<Cue, Long>{
    List<Cue> findById(String id);
}