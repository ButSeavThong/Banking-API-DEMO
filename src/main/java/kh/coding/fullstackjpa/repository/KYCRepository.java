
package kh.coding.fullstackjpa.repository;
import kh.coding.fullstackjpa.domain.KYC;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface KYCRepository
        extends
        CrudRepository
                <KYC, Integer> {

    Optional<KYC> findByCustomerId(Integer customerId);
}
