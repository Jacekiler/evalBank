package js.customer.service;

import js.customer.entity.Customer;
import js.customer.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public int saveCustomer(Customer customer){
        return customerDao.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerDao.getAll();
    }

}
