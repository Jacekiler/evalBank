package js.customer.repository;

import js.customer.entity.entity.Customer;

import java.util.List;

public interface CustomerDao {

    int save(Customer customer);

    Customer[] getByIds();
}
