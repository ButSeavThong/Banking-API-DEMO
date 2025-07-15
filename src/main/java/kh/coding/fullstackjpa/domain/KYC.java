package kh.coding.fullstackjpa.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class KYC {
    // KYC  Know Your Customer : some credetial to modify user or customer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nationalCardId;
    private Boolean isVerified;
    private Boolean isDeleted;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name="cust_id")
    private Customer customer;

}
