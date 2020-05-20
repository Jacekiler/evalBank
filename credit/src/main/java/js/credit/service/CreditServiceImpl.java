package js.credit.service;

import js.credit.model.entity.Credit;
import js.credit.model.entity.CreditDetails;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.result.dto.CreditDTO;
import js.credit.model.result.dto.mapper.DTOMapper;
import js.credit.repository.CreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements CreditService {

    private static final String BASE_CUSTOMER_URL = "http://localhost:8081/customer/";
    private static final String BASE_PRODUCT_URL = "http://localhost:8082/product/";
//    private static final String BASE_CUSTOMER_URL = "http://customercontroller:8081/customer/";
//    private static final String BASE_PRODUCT_URL = "http://productcontroller:8082/product/";
    private static final String CREATE_CUSTOMER_URL = BASE_CUSTOMER_URL + "createCustomer";
    private static final String CREATE_PRODUCT_URL = BASE_PRODUCT_URL + "createProduct";

    @Autowired
    private CreditDao creditDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DTOMapper dtoMapper;

    public int processCredit(CreditDetails creditDetails){
        int creditId = creditDao.save(creditDetails.getCredit());
        Optional<Integer> customerId = createCustomerRequest(creditDetails);
        Optional<Integer> productId = createProductRequest(creditDetails);
        return creditId;
    }

    private Optional<Integer> createCustomerRequest(CreditDetails creditDetails){
        Customer customer = creditDetails.getCustomer();
        customer.setCredit(creditDetails.getCredit());
        ResponseEntity<Integer> customerResponse = restTemplate.postForEntity(CREATE_CUSTOMER_URL, customer, Integer.class);
        return Optional.ofNullable(customerResponse.getBody());
    }

    private Optional<Integer> createProductRequest(CreditDetails creditDetails){
        Product product = creditDetails.getProduct();
        product.setCredit(creditDetails.getCredit());
        ResponseEntity<Integer> productResponse = restTemplate.postForEntity(CREATE_PRODUCT_URL, product, Integer.class);
        return Optional.ofNullable(productResponse.getBody());
    }

    public List<CreditDTO> getAllCredits() {
        List<Credit> credits = creditDao.getAll();
        return dtoMapper.mapCreditDto(credits);
    }
}
