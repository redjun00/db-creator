package hello.query;

import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import hello.domain.Customer;
import hello.domain.QCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CustomerQueryer {

    @Autowired
    @PersistenceContext         //TODO 뭐하는 것?
    private EntityManager entityManager;

    @Transactional
    public List<Customer> getCustomers(){
        QCustomer customer = QCustomer.customer;
        JPAQuery query = new JPAQuery(entityManager);
        List<Customer> cueList = query.from(customer).list(customer);
        return cueList;
    }

    @Transactional
    public Customer getCustomerById(Long idToFind){
        QCustomer customer = QCustomer.customer;
        JPAQuery query = new JPAQuery(entityManager);
        Customer finedCustomer = query.from(customer)
                .where(customer.id.eq(idToFind))
                .uniqueResult(customer);

        return finedCustomer;
    }

    @Transactional
    public void saveCustomers(Customer customerToSave){
        QCustomer customer = QCustomer.customer;

        //TODO connection..?
//        JPAQuery query = new SQLInsertClause(connection, customer, customerToSave);
    }

    @Transactional
    public void deleteCustomerById(Long idToDelete){
        System.out.println("** ready delete=" + idToDelete);
        QCustomer customer = QCustomer.customer;
        new JPADeleteClause(entityManager, customer)
                .where(customer.id.eq(idToDelete)).execute();
    }

    @Transactional
    public void updateCustomer(Customer costomerToUpdate){
        QCustomer customer = QCustomer.customer;
        new JPAUpdateClause(entityManager, customer)
                .where(customer.id.eq(costomerToUpdate.getId()))
                .set(customer.name, costomerToUpdate.getName())
                .set(customer.address, costomerToUpdate.getAddress())
                .execute();
    }

    //TODO join order? db에서 일반적으로 사용하는 부분이 뭔지..
}
