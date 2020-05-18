package js.credit.service;

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
    private static final String GET_CUSTOMERS_URL = BASE_CUSTOMER_URL + "getCustomers";
    private static final String GET_PRODUCTS_URL = BASE_PRODUCT_URL + "getProducts";

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
        customer.setCreditId(creditDetails.getCredit().getId());
        ResponseEntity<Integer> customerResponse = restTemplate.postForEntity(CREATE_CUSTOMER_URL, customer, Integer.class);
        return Optional.ofNullable(customerResponse.getBody());
    }

    private Optional<Integer> createProductRequest(CreditDetails creditDetails){
        Product product = creditDetails.getProduct();
        product.setCreditId(creditDetails.getCredit().getId());
        ResponseEntity<Integer> productResponse = restTemplate.postForEntity(CREATE_PRODUCT_URL, product, Integer.class);
        return Optional.ofNullable(productResponse.getBody());
    }

    public List<CreditDetails> getAllCredits(){
        List<Credit> credits = creditDao.getAll();
        List<Customer> customers = getCustomers();
        List<CreditDetails> creditDetails = aggregateCustomers(credits, customers);
        List<Product> products = getProducts();
        aggregateProducts(creditDetails, products);
        return creditDetails;
    }

    private List<Customer> getCustomers(){
        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(GET_CUSTOMERS_URL, Customer[].class);
        return Optional.ofNullable(responseEntity.getBody()).map(Arrays::asList)
                .orElse(new ArrayList<>());
    }

    private List<CreditDetails> aggregateCustomers(List<Credit> credits, List<Customer> customers){
        return credits.stream()
                .flatMap(credit -> customers.stream()
                        .filter(customer -> customer.getCreditId() == credit.getId())
                        .map(customer -> new CreditDetails(credit, customer)))
                .collect(Collectors.toList());
    }

    private List<Product> getProducts(){
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(GET_PRODUCTS_URL, Product[].class);
        return Optional.ofNullable(responseEntity.getBody()).map(Arrays::asList)
                .orElse(new ArrayList<>());
    }

    private void aggregateProducts(List<CreditDetails> creditDetails, List<Product> products){
        creditDetails.forEach(creditDetail -> products.stream()
                        .filter(product -> product.getCreditId() == creditDetail.getCredit().getId())
                        .findFirst()
                        .ifPresent(creditDetail::setProduct));
    }

}
