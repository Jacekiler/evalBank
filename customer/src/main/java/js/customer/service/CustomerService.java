package js.customer.service;

import js.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
     int saveCustomer(Customer customer);

     List<Customer> getAllCustomers();
}
