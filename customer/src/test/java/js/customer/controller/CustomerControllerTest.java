package js.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import js.customer.entity.Customer;
import js.customer.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerControllerTest {

    private static final int CUSTOMER_1_ID = 1;
    private static final int CUSTOMER_2_ID = 2;
    private static final String CUSTOMER_1_NAME = "Jon";
    private static final String CUSTOMER_2_NAME = "Jane";
    private static final String CUSTOMER_1_SURNAME = "Middle";
    private static final String CUSTOMER_2_SURNAME = "Knox";
    private static final String CUSTOMER_1_PESEL = "93062255555";
    private static final String CUSTOMER_2_PESEL = "95062233333";

    private static final String POST_ADDRESS = "/customer/createCustomer";
    private static final String GET_ADDRESS = "/customer/getCustomers";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomer() throws Exception {
        Assertions.assertEquals(customerService.getAllCustomers().size(), 0);
        mockMvc.perform(post(POST_ADDRESS).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(prepareCustomer(null, CUSTOMER_1_NAME, CUSTOMER_1_SURNAME, CUSTOMER_1_PESEL))))
                .andExpect(status().isOk());
        Assertions.assertEquals(customerService.getAllCustomers().size(), 1);
    }

    @Test
    void getCustomers() throws Exception {
        customerService.saveCustomer(prepareCustomer(CUSTOMER_1_ID, CUSTOMER_1_NAME, CUSTOMER_1_SURNAME, CUSTOMER_1_PESEL));
        customerService.saveCustomer(prepareCustomer(CUSTOMER_2_ID, CUSTOMER_2_NAME, CUSTOMER_2_SURNAME, CUSTOMER_2_PESEL));
        mockMvc.perform(get(GET_ADDRESS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(CUSTOMER_1_ID)))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(CUSTOMER_2_ID)))
                .andExpect(jsonPath("$[0].firstName", Matchers.equalTo(CUSTOMER_1_NAME)))
                .andExpect(jsonPath("$[1].firstName", Matchers.equalTo(CUSTOMER_2_NAME)))
                .andExpect(jsonPath("$[0].surname", Matchers.equalTo(CUSTOMER_1_SURNAME)))
                .andExpect(jsonPath("$[1].surname", Matchers.equalTo(CUSTOMER_2_SURNAME)))
                .andExpect(jsonPath("$[0].pesel", Matchers.equalTo(CUSTOMER_1_PESEL)))
                .andExpect(jsonPath("$[1].pesel", Matchers.equalTo(CUSTOMER_2_PESEL)));
    }

    private Customer prepareCustomer(Integer id, String name, String surname, String pesel){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(name);
        customer.setSurname(surname);
        customer.setPesel(pesel);
        return customer;
    }
}