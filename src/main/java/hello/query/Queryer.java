package hello.query;

import com.mysema.query.jpa.impl.JPAQuery;
import hello.domain.Customer;
import hello.domain.QCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class Queryer {

    @Autowired
    @PersistenceContext         //TODO 뭐하는 것?
    private EntityManager entityManager;


    public List<Customer> getCueTable(){
        QCustomer qCustomer = QCustomer.customer;
        JPAQuery query = new JPAQuery(entityManager);
        List<Customer> cueList = query.from(qCustomer).list(qCustomer);
        return cueList;
    }
}
