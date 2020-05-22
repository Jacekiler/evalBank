package js.credit.model.inout.dto.mapper;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.inout.dto.CreditDTO;
import js.credit.model.inout.dto.CustomerDTO;
import js.credit.model.inout.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DTOMapperTest {

    private static final String CREDIT_NAME = "credit1";
    private static final Integer CREDIT_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final String PRODUCT_NAME = "product1";
    private static final Double PRODUCT_VALUE = 17.5;
    private static final Integer CUSTOMER_ID = 1;
    private static final String CUSTOMER_NAME = "Jon";
    private static final String CUSTOMER_SURNAME = "Knox";
    private static final String CUSTOMER_PESEL = "95042223341";

    private DTOMapper dtoMapper = new DTOMapper();

    @Test
    void mapCreditDto() {
        Credit credit = prepareCredit();
        CreditDTO creditDTO = dtoMapper.mapCreditEntityToDTO(credit);
        assertEquals(creditDTO.getId(), CREDIT_ID);
        assertEquals(creditDTO.getName(), CREDIT_NAME);
    }

    @Test
    public void mapProductDTO(){
        Product product = prepareProduct();
        ProductDTO productDTO = dtoMapper.mapProducEntityToDTO(product);
        assertEquals(productDTO.getId(), PRODUCT_ID);
        assertEquals(productDTO.getProductName(), PRODUCT_NAME);
        assertEquals(productDTO.getValue(), PRODUCT_VALUE);
    }

    @Test
    public void mapCustomerDTO(){
        Customer customer = prepareCustomer();
        CustomerDTO customerDTO = dtoMapper.mapCustomerEntityToDTO(customer);
        assertEquals(customerDTO.getId(), CUSTOMER_ID);
        assertEquals(customerDTO.getFirstName(), CUSTOMER_NAME);
        assertEquals(customerDTO.getSurname(), CUSTOMER_SURNAME);
        assertEquals(customerDTO.getPesel(), CUSTOMER_PESEL);
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
        product.setValue(PRODUCT_VALUE);
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