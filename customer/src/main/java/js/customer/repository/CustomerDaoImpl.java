package js.customer.repository;

import js.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public int save(Customer customer) {
        return (int) hibernateTemplate.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return hibernateTemplate.loadAll(Customer.class);
    }
}
