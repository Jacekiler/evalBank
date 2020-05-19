package js.credit.model.result.dto.mapper;

import js.credit.model.entity.Credit;
import js.credit.model.entity.Customer;
import js.credit.model.entity.Product;
import js.credit.model.result.dto.CreditDTO;
import js.credit.model.result.dto.CustomerDTO;
import js.credit.model.result.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    public CreditDTO mapCreditDto(Credit credit){
        return new CreditDTO(credit.getId(), credit.getName(), mapCustomerDto(credit.getCustomer()), mapProducDto(credit.getProduct()));
    }

    public List<CreditDTO> mapCreditDto(List<Credit> credits){
        return credits.stream()
                .map(credit -> new CreditDTO(credit.getId(), credit.getName(), mapCustomerDto(credit.getCustomer()), mapProducDto(credit.getProduct())))
                .collect(Collectors.toList());
    }

    private CustomerDTO mapCustomerDto(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getSurname(), customer.getPesel());
    }

    private ProductDTO mapProducDto(Product product){
        return new ProductDTO(product.getId(), product.getProductName());
    }
}
