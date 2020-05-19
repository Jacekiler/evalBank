package js.credit.model.result.dto;

import java.io.Serializable;

public class CreditDTO implements Serializable {

    private final Integer id;
    private final String name;
    private final CustomerDTO customer;
    private final ProductDTO product;

    public CreditDTO(Integer id, String name, CustomerDTO customer, ProductDTO product) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
