package js.credit.model.result.dto.mapper;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.result.dto.CreditDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DTOMapperTest {

    private static final String CREDIT_NAME = "credit1";
    private static final Integer CREDIT_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final String PRODUCT_NAME = "product1";
    private static final Integer CUSTOMER_ID = 1;
    private static final String CUSTOMER_NAME = "Jon";
    private static final String CUSTOMER_SURNAME = "Knox";
    private static final String CUSTOMER_PESEL = "95042223341";

    private DTOMapper dtoMapper = new DTOMapper();

    @Test
    void mapCreditDto() {
        Credit credit = prepareCredit();
        CreditDTO creditDTO = dtoMapper.mapCreditDto(credit);
        assertEquals(creditDTO.getId(), CREDIT_ID);
        assertEquals(creditDTO.getName(), CREDIT_NAME);
        assertEquals(creditDTO.getProduct().getId(), PRODUCT_ID);
        assertEquals(creditDTO.getProduct().getProductName(), PRODUCT_NAME);
        assertEquals(creditDTO.getCustomer().getId(), CUSTOMER_ID);
        assertEquals(creditDTO.getCustomer().getFirstName(), CUSTOMER_NAME);
        assertEquals(creditDTO.getCustomer().getSurname(), CUSTOMER_SURNAME);
        assertEquals(creditDTO.getCustomer().getPesel(), CUSTOMER_PESEL);
    }

    private Credit prepareCredit(){
        Credit credit = new Credit();
        credit.setName(CREDIT_NAME);
        credit.setId(CREDIT_ID);
        credit.setProduct(prepareProduct());
        credit.setCustomer(prepareCustomer());
        return credit;
    }

    private Product prepareProduct(){
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setProductName(PRODUCT_NAME);
        return product;
    }

    private Customer prepareCustomer(){
        Customer customer = new Customer();
        customer.setFirstName(CUSTOMER_NAME);
        customer.setSurname(CUSTOMER_SURNAME);
        customer.setId(CUSTOMER_ID);
        customer.setPesel(CUSTOMER_PESEL);
        return customer;
    }
}