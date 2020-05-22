package js.credit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.inout.dto.CreditDTO;
import js.credit.model.inout.dto.CreditDetailsDTO;
import js.credit.model.inout.dto.CustomerDTO;
import js.credit.model.inout.dto.ProductDTO;
import js.credit.repository.CreditDaoImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreditControllerTest {

    private static final String PRODUCT_NAME = "product1";
    private static final Double PRODUCT_VALUE = 15.5;
    private static final String CREDIT_1_NAME = "credit1";
    private static final Integer CREDIT_1_ID = 1;
    private static final String CUSTOMER_NAME = "Jon";
    private static final String CUSTOMER_SURNAME = "Middle";
    private static final String CUSTOMER_PESEL = "93062255555";

    private static final String POST_ADDRESS = "/credit/createCredit";
    private static final String GET_ADDRESS = "/credit/getCredits";
    private static final String CUSTOMER_URL = "http://customercontroller:8081/customer/createCustomer";
    private static final String PRODUCT_URL = "http://productcontroller:8082/product/createProduct";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditDaoImpl creditDao;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeAll
    public void init() throws URISyntaxException {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
        mockRestTemplate(CUSTOMER_URL);
        mockRestTemplate(PRODUCT_URL);
    }

    @BeforeEach
    public void setup(){
        when(creditDao.getAll()).thenReturn(Lists.newArrayList(prepareCreditWithRelations()));
    }

    @Test
    void createCreditWithValidRequest() throws Exception {
        CreditDetailsDTO creditDetailsDTO = prepareValidCreditDetailsDTO();
        mockMvc.perform(post(POST_ADDRESS).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(creditDetailsDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void createCreditWithInvalidRequest() throws Exception {
        mockMvc.perform(post(POST_ADDRESS).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(prepareInvalidCreditDetailsDTO())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCredits() throws Exception {
        mockMvc.perform(get(GET_ADDRESS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].creditDTO.name", Matchers.equalTo(CREDIT_1_NAME)));
    }

    private void mockRestTemplate(String uri) throws URISyntaxException {
        mockRestServiceServer.expect(ExpectedCount.twice(), MockRestRequestMatchers.requestTo(new URI(uri)))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK));
    }

    private CreditDetailsDTO prepareValidCreditDetailsDTO(){
        CreditDTO creditDTO = new CreditDTO(null, CREDIT_1_NAME);
        ProductDTO productDTO = new ProductDTO(null, PRODUCT_NAME, PRODUCT_VALUE);
        CustomerDTO customerDTO = new CustomerDTO(null, CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_PESEL);
        return new CreditDetailsDTO(creditDTO, productDTO, customerDTO);
    }

    private CreditDetailsDTO prepareInvalidCreditDetailsDTO(){
        CreditDTO creditDTO = new CreditDTO(null, CREDIT_1_NAME);
        return new CreditDetailsDTO(creditDTO, null, null);
    }

    private Credit prepareCreditWithRelations(){
        Credit credit = new Credit();
        credit.setId(CREDIT_1_ID);
        credit.setName(CREDIT_1_NAME);
        credit.setCustomer(new Customer());
        credit.setProduct(new Product());
        return credit;
    }
}