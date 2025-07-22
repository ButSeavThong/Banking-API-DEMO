package kh.coding.fullstackjpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false )
    private String password;

    @Column(nullable = false)
    private boolean isEnabled;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns =
                    @JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "role_id", referencedColumnName = "id")

    )
    private List<Role> roles;
}
