package js.customer.repository;

import js.customer.entity.Customer;

import java.util.List;

public interface CustomerDao {

    int save(Customer customer);

    List<Customer> getAll();
}
