package kh.coding.fullstackjpa.initialize;

import jakarta.annotation.PostConstruct;
import kh.coding.fullstackjpa.domain.AccountType;
import kh.coding.fullstackjpa.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountTypeInit {
    private final AccountTypeRepository accountTypeRepository;
    @PostConstruct
    void initAccountTypes() {
        if(accountTypeRepository.count() == 0) {
            AccountType saving = new AccountType();
            saving.setType("Saving");
            saving.setDeleted(false);

            AccountType payroll = new AccountType();
            payroll.setType("Payroll");
            payroll.setDeleted(false);

            accountTypeRepository.saveAll(List.of(saving, payroll));
        }
    }
}
