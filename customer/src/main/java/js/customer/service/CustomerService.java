package js.customer.service;

import js.customer.entity.Customer;
import js.customer.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public int processCustomer(Customer customer){
        return customerDao.save(customer);
    }

    public Customer[] getCustomers(){
        return customerDao.getByIds();
    }

}
