package kh.coding.fullstackjpa.repository;

import kh.coding.fullstackjpa.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends
        JpaRepository
                <AccountType, Integer> {

    Optional<AccountType> findById(Integer id);
}
