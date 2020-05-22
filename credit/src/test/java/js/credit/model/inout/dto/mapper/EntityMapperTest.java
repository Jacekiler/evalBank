package js.credit.model.inout.dto.mapper;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.inout.dto.CreditDTO;
import js.credit.model.inout.dto.CustomerDTO;
import js.credit.model.inout.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityMapperTest {

    private static final Integer CREDIT_ID = 1;
    private static final String CREDIT_NAME = "credit1";
    private static final Integer PRODUCT_ID = 1;
    private static final String PRODUCT_NAME = "product1";
    private static final Double PRODUCT_VALUE = 20.0;
    private static final Integer CUSTOMER_ID = 1;
    private static final String CUSTOMER_NAME = "Jamie";
    private static final String CUSTOMER_SURNAME = "Knowles";
    private static final String CUSTOMER_PESEL = "96052234567";

    private EntityMapper entityMapper = new EntityMapper();

    @Test
    void mapCreditDTOToEntity() {
        CreditDTO creditDTO = new CreditDTO(CREDIT_ID, CREDIT_NAME);
        Credit credit = entityMapper.mapCreditDTOToEntity(creditDTO);
        assertEquals(credit.getId(), CREDIT_ID);
        assertEquals(credit.getName(), CREDIT_NAME);
    }

    @Test
    void mapProductDTOToEntity() {
        ProductDTO productDTO = new ProductDTO(PRODUCT_ID, PRODUCT_NAME, PRODUCT_VALUE);
        Product product = entityMapper.mapProductDTOToEntity(productDTO);
        assertEquals(product.getId(), PRODUCT_ID);
        assertEquals(product.getProductName(), PRODUCT_NAME);
        assertEquals(product.getValue(), PRODUCT_VALUE);
    }

    @Test
    void mapCustomerDTOToEntity() {
        CustomerDTO customerDTO = new CustomerDTO(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_PESEL);
        Customer customer = entityMapper.mapCustomerDTOToEntity(customerDTO);
        assertEquals(customer.getId(), CUSTOMER_ID);
        assertEquals(customer.getFirstName(), CUSTOMER_NAME);
        assertEquals(customer.getSurname(), CUSTOMER_SURNAME);
        assertEquals(customer.getPesel(), CUSTOMER_PESEL);
    }
}