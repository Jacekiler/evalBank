package js.customer.controller;

import js.customer.entity.Customer;
import js.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/createCustomer")
    public int createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping(value = "/getCustomers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

}
