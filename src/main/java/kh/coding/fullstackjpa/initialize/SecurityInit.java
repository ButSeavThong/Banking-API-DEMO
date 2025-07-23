package kh.coding.fullstackjpa.initialize;

import jakarta.annotation.PostConstruct;
import kh.coding.fullstackjpa.domain.Role;
import kh.coding.fullstackjpa.domain.User;
import kh.coding.fullstackjpa.repository.RoleRepository;
import kh.coding.fullstackjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityInit {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void intit() {

        Role defaultRole = new Role();
        defaultRole.setRole("USER");

        Role admin = new Role();
        admin.setRole("ADMIN");

        Role staff = new Role();
        staff.setRole("STAFF");

        Role customer = new Role();
        customer.setRole("CUSTOMER");

        if(roleRepository.count() <1 ) {
            roleRepository.saveAll(List.of(defaultRole, admin, staff, customer));
        }

        if (userRepository.count() < 1) {

            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(passwordEncoder.encode("pwd@123"));
            userAdmin.setEnabled(true);
            userAdmin.setRoles(List.of(defaultRole, admin));

            User userStaff = new User();
            userStaff.setUsername("staff");
            userStaff.setPassword(passwordEncoder.encode("pwd@123"));
            userStaff.setEnabled(true);
            userStaff.setRoles(List.of(defaultRole, staff));

            User userCustomer = new User();
            userCustomer.setUsername("customer");
            userCustomer.setPassword(passwordEncoder.encode("pwd@123"));
            userCustomer.setEnabled(true);
            userCustomer.setRoles(List.of(defaultRole, customer));

            userRepository.saveAll(List.of(userAdmin,userStaff,userCustomer));
        }   
    }

}
