package kh.coding.fullstackjpa.repository;

import kh.coding.fullstackjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository
        extends
        JpaRepository
                <Customer, Integer> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<Customer> findCustomerByPhone(String phone);
}
