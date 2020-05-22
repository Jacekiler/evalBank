package js.credit.model.inout.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/*
    DTOs to handle external requests and responses. Remove loops e.g. Credit -> Product -> Credit -> ...
 */
public class CreditDetailsDTO {

    @NotNull
    @Valid
    private final CreditDTO creditDTO;

    @NotNull
    @Valid
    private final ProductDTO productDTO;

    @NotNull
    @Valid
    private final CustomerDTO customerDTO;

    public CreditDetailsDTO(CreditDTO creditDTO, ProductDTO productDTO, CustomerDTO customerDTO) {
        this.creditDTO = creditDTO;
        this.productDTO = productDTO;
        this.customerDTO = customerDTO;
    }

    public CreditDTO getCreditDTO() {
        return creditDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
}
