package js.credit.model.result.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private final int id;
    private final String firstName;
    private final String surname;
    private final String pesel;

    public CustomerDTO(int id, String firstName, String surname, String pesel) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
    }

    public int getId() {
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
