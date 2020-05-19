package js.credit.model.result.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private final Integer id;
    private final String productName;

    public ProductDTO(Integer id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
}
