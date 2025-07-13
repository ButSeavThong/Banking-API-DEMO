package kh.coding.fullstackjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="customers")
@Setter
@Getter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length=25)
    private String fullName;

    @Column(length=10, nullable=false)
    private String gender;


    @Column(unique=true, nullable=true)
    private String email;


    @Column(unique=true, nullable=true)
    private String phone;


    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable=false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private KYC kyc;

}
