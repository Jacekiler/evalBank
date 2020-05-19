package js.credit.model.entity;

import javax.validation.constraints.NotNull;

public class CreditDetails {

    @NotNull
    private Credit credit;

    @NotNull
    private Customer customer;

    @NotNull
    private Product product;

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
