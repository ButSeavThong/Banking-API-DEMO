package kh.coding.fullstackjpa.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="account_types")
public class AccountType {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

    @Column(length = 100, nullable = false, unique=true)
    private String type;

    @Column( nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;

}
