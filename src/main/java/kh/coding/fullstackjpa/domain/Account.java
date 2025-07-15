package kh.coding.fullstackjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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


    @Column(nullable=false, length=25)
    private String actCurrency;

    @Column(nullable=false)
    private Boolean isDeleted = false;

    private Double overLimit;


    @ManyToOne(optional = false)
    @JoinColumn(name="cust_id",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    private AccountType accountType;


    @OneToMany(mappedBy = "sender")
    private List<Transaction> transaction;


}
