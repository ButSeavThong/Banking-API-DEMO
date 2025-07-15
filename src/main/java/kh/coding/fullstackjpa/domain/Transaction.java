package kh.coding.fullstackjpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double amount;

    private String remarks;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne(optional = false)
    private TransactionType transctionType;

    @ManyToOne(optional = false)
    private Account sender;

    @ManyToOne(optional = false)
    private Account receiver;

}
