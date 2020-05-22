package js.credit.service;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.inout.dto.CreditDetailsDTO;
import js.credit.model.inout.dto.mapper.DTOMapper;
import js.credit.model.inout.dto.mapper.EntityMapper;
import js.credit.repository.CreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    // Using services names from docker-compose.yml
    private static final String CREATE_CUSTOMER_URL = "http://customercontroller:8081/customer/createCustomer";
    private static final String CREATE_PRODUCT_URL = "http://productcontroller:8082/product/createProduct";

    @Autowired
    private CreditDao creditDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private EntityMapper entityMapper;

    public int processCredit(CreditDetailsDTO creditDetailsDTO){
        Credit credit = entityMapper.mapCreditDTOToEntity(creditDetailsDTO.getCreditDTO());
        int creditId = creditDao.save(credit);
        createCustomerRequest(credit, entityMapper.mapCustomerDTOToEntity(creditDetailsDTO.getCustomerDTO()));
        createProductRequest(credit, entityMapper.mapProductDTOToEntity(creditDetailsDTO.getProductDTO()));
        return creditId;
    }

    private void createCustomerRequest(Credit credit, Customer customer){
        customer.setCredit(credit);
        restTemplate.postForEntity(CREATE_CUSTOMER_URL, customer, Integer.class);
        System.out.println();
    }

    private void createProductRequest(Credit credit, Product product){
        product.setCredit(credit);
        restTemplate.postForEntity(CREATE_PRODUCT_URL, product, Integer.class);
        System.out.println();
    }

    /*
        Because of @OneToOne relation, getting all credits contains all related products and customers as well. No need to send internal requests
        to Customer's/Product's controller for data.
     */
    public List<CreditDetailsDTO> getAllCredits() {
        List<Credit> credits = creditDao.getAll();
        return dtoMapper.mapCreditDetailsDTO(credits);
    }
}
