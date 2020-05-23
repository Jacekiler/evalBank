package js.credit.model.inout.dto.mapper;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.inout.dto.CreditDTO;
import js.credit.model.inout.dto.CustomerDTO;
import js.credit.model.inout.dto.ProductDTO;
import org.springframework.stereotype.Component;

/*
    Mapping entities from external request (no relations between Credit and Product/Customer)
    into entities for internal communication and database storage (availability of relations)
 */

@Component
public class EntityMapper {

    public Credit mapCreditDTOToEntity(CreditDTO creditDTO){
        Credit credit = new Credit();
        credit.setId(creditDTO.getId());
        credit.setName(creditDTO.getName());
        return credit;
    }

    public Product mapProductDTOToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setValue(productDTO.getValue());
        return product;
    }

    public Customer mapCustomerDTOToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPesel(customerDTO.getPesel());
        return customer;
    }
}
