package js.customer.repository;

import js.customer.entity.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public int save(Customer customer) {
        return (int) hibernateTemplate.save(customer);
    }

    @Override
    public Customer[] getByIds() {
        List<Customer> customers = hibernateTemplate.loadAll(Customer.class);
        Customer[] customersArray = new Customer[customers.size()];
        customers.toArray(customersArray);
        return customersArray;
    }
}
