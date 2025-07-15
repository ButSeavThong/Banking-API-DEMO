package kh.coding.fullstackjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="segments")
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true,nullable=false)
    private String segment;

    private String description;

    @Column( nullable = false)
    private boolean isDeleted;

    // one segment aply to many account
    @OneToMany(mappedBy = "segment")
    private List<Customer> customers;


}
