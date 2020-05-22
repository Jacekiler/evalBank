package js.credit.model.inout.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
    DTOs to handle external requests and responses. Remove loops e.g. Credit -> Product -> Credit -> ...
 */
public class ProductDTO implements Serializable {

    private final Integer id;

    @NotBlank
    private final String productName;

    @NotNull
    private final Double value;

    public ProductDTO(Integer id, String productName, Double value) {
        this.id = id;
        this.productName = productName;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Double getValue() {
        return value;
    }
}
