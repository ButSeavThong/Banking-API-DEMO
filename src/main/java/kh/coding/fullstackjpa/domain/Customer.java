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
    private String phoneNumber;

    @Column(unique=true)
    private String nationalCardId;


    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable=false)
    private Boolean isDeleted; // soft deleted ( just disabled )  // hard deleted = delete permanantly

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private KYC kyc;


    // many customer can i have on segment !
    @ManyToOne(optional = false)
    private Segment segment;

}
