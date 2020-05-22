package js.credit.model.inout.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/*
    DTOs to handle external requests and responses. Remove loops e.g. Credit -> Product -> Credit -> ...
 */
public class CustomerDTO implements Serializable {

    private final Integer id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String surname;

    @NotBlank
    private final String pesel;

    public CustomerDTO(Integer id, String firstName, String surname, String pesel) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }
}
