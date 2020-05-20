package js.customer.repository;

import js.customer.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerDaoImplTest {

    private static final String CUSTOMER_NAME = "Kate";
    private static final String CUSTOMER_PESEL = "94235552123";
    private static final String CUSTOMER_SURNAME = "Mate";

    @Autowired
    private CustomerDao customerDao;

    @Test
    void saveAndGetAll() {
        List<Customer> customersBeforeSaving = customerDao.getAll();
        int customerid = customerDao.save(prepareCustomer());
        List<Customer> customersAfterSaving = customerDao.getAll();

        Assertions.assertEquals(customersBeforeSaving.size(), 0);
        Assertions.assertEquals(customersAfterSaving.size(), 1);
        Assertions.assertTrue(customerid > 0);
        validateCustomer(customersAfterSaving.get(0));
    }

    private void validateCustomer(Customer customer){
        Assertions.assertEquals(customer.getFirstName(), CUSTOMER_NAME);
        Assertions.assertEquals(customer.getSurname(), CUSTOMER_SURNAME);
        Assertions.assertEquals(customer.getPesel(), CUSTOMER_PESEL);
    }

    private Customer prepareCustomer(){
        Customer customer = new Customer();
        customer.setSurname(CUSTOMER_SURNAME);
        customer.setPesel(CUSTOMER_PESEL);
        customer.setFirstName(CUSTOMER_NAME);
        return customer;
    }


}