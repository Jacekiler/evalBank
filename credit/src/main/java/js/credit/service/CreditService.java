package js.credit.service;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.Lists;
import js.credit.entity.Credit;
import js.credit.entity.CreditDetails;
import js.credit.entity.Customer;
import js.credit.entity.Product;
import js.credit.repository.CreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreditService {

    private static final String BASE_CUSTOMER_URL = "http://localhost:8081/customer/";
    private static final String BASE_PRODUCT_URL = "http://localhost:8082/product/";
    private static final String CREATE_CUSTOMER_URL = BASE_CUSTOMER_URL + "createCustomer";
    private static final String CREATE_PRODUCT_URL = BASE_PRODUCT_URL + "createProduct";
//    private static final String GET_CUSTOMERS_URL = BASE_CUSTOMER_URL + "getCustomers";
//    private static final String GET_PRODUCTS_URL = BASE_PRODUCT_URL + "getProducts";

    @Autowired
    private CreditDao creditDao;

    @Autowired
    private RestTemplate restTemplate;

    public int processCredit(CreditDetails creditDetails){
        int creditId = creditDao.save(creditDetails.getCredit());

        Optional<Integer> customerId = createCustomerRequest(creditDetails);
        Optional<Integer> productId = createProductRequest(creditDetails);

        return creditId;
    }

    private Optional<Integer> createCustomerRequest(CreditDetails creditDetails){
        Customer customer = creditDetails.getCustomer();
        if(customer == null){
            return Optional.empty();
        }
        customer.setCredit(creditDetails.getCredit());
        ResponseEntity<Integer> customerResponse = restTemplate.postForEntity(CREATE_CUSTOMER_URL, customer, Integer.class);
        return Optional.ofNullable(customerResponse.getBody());
    }

    private Optional<Integer> createProductRequest(CreditDetails creditDetails){
        Product product = creditDetails.getProduct();
        if(product == null){
            return Optional.empty();
        }
        product.setCredit(creditDetails.getCredit());
        ResponseEntity<Integer> productResponse = restTemplate.postForEntity(CREATE_PRODUCT_URL, product, Integer.class);
        return Optional.ofNullable(productResponse.getBody());
    }

    public List<Credit> getAllCredits() {
        List<Credit> credits = creditDao.getAll();
        return prepareResponse(credits);
    }

    private List<Credit> prepareResponse(List<Credit> credits){
        return credits.stream()
                .peek(credit -> {
                    if(credit.getCustomer() != null) {
                        credit.getCustomer().setCredit(null);
                    }
                    if(credit.getProduct() != null) {
                        credit.getProduct().setCredit(null);
                    }
                }).collect(Collectors.toList());
    }
}
