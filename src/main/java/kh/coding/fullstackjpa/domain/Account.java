package kh.coding.fullstackjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true , length=20)
    private String accountNumber;

    @Column(nullable=false, length=25 )
    private Double balance;

    @Column(nullable=false,length=25)
    private String accountType;

    @Column(nullable=false, length=25)
    private String actCurrency;

    @Column(nullable=false)
    Boolean isDeleted;


    @ManyToOne
    @JoinColumn(name="cust_id",referencedColumnName = "id")
    private Customer customer;
}
