package js.customer.entity.entity;

import javax.persistence.*;

@Entity
@Table(name = "pr5")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String productName;

//    private Integer creditId;

    @OneToOne
    private Credit credit;

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

//    public Integer getCreditId() {
//        return creditId;
//    }
//
//    public void setCreditId(Integer creditId) {
//        this.creditId = creditId;
//    }
}
