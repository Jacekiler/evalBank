package js.credit.model.inout.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
    DTOs to handle external requests and responses. Remove loops e.g. Credit -> Product -> Credit -> ...
 */
public class CreditDTO implements Serializable {

    private final Integer id;

    @NotBlank
    private final String name;

    public CreditDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
