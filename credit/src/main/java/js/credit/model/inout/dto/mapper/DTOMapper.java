package js.credit.model.inout.dto.mapper;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.inout.dto.CreditDTO;
import js.credit.model.inout.dto.CreditDetailsDTO;
import js.credit.model.inout.dto.CustomerDTO;
import js.credit.model.inout.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    public CreditDTO mapCreditEntityToDTO(Credit credit){
        return new CreditDTO(credit.getId(), credit.getName());
    }

    public List<CreditDetailsDTO> mapCreditDetailsDTO(List<Credit> credits){
        return credits.stream()
                .map(credit -> new CreditDetailsDTO(mapCreditEntityToDTO(credit), mapProducEntityToDTO(credit.getProduct()),
                                mapCustomerEntityToDTO(credit.getCustomer())))
                .collect(Collectors.toList());
    }

    public CustomerDTO mapCustomerEntityToDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getSurname(), customer.getPesel());
    }

    public ProductDTO mapProducEntityToDTO(Product product){
        return new ProductDTO(product.getId(), product.getProductName(), product.getValue());
    }
}
