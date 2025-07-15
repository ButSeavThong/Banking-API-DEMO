package kh.coding.fullstackjpa.repository;

import kh.coding.fullstackjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository
        extends
        JpaRepository
                <Customer, Integer> {

    // JPQL
    @Modifying
    @Query(value=
            """
            SELECT EXISTS(SELECT C FROM Customer C WHERE C.phoneNumber = :phoneNumber)
            """)
    boolean isExistsByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = """
       UPDATE Customer c SET c.isDeleted = TRUE
              WHERE c.phoneNumber = :phoneNumber
       """)
    void disableByPhoneNumber(String phoneNumber);


    // derrived query
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);

    Optional<Customer> findCustomerByIsDeletedFalse();

    boolean existsByNationalCardId(String nationalCardId);

}
