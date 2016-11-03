package hello.query;

import com.mysema.query.jpa.impl.JPAQuery;
import hello.domain.Cue;
import domain.QCue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class Queryer {

    @Autowired
    @PersistenceContext         //뭐하는 것?
    private EntityManager entityManager;


    public List<Cue> getCueTable(){
        QCue qCue = QCue.cue;
        JPAQuery query = new JPAQuery(entityManager);
        List<Cue> cueList = query.from(qCue).list(qCue);
        return cueList;
    }
}
