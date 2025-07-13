package kh.coding.fullstackjpa.repository;

import kh.coding.fullstackjpa.domain.KYC;
import org.springframework.data.repository.CrudRepository;

public interface KYCRepository
        extends
        CrudRepository
                <KYC, Integer> {

}
