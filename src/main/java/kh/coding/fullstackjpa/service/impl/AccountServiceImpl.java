package kh.coding.fullstackjpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kh.coding.fullstackjpa.domain.Account;
import kh.coding.fullstackjpa.domain.Customer;
import kh.coding.fullstackjpa.dto.AccountResponse;
import kh.coding.fullstackjpa.dto.CreateAccountRequest;
import kh.coding.fullstackjpa.dto.UpdateAccountRequest;
import kh.coding.fullstackjpa.mapper.AccountMapper;
import kh.coding.fullstackjpa.repository.AccountRepository;
import kh.coding.fullstackjpa.repository.CustomerRepository;
import kh.coding.fullstackjpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;

    @Override

            public AccountResponse createNewAccount(CreateAccountRequest createAccountRequest) {

            if(accountRepository.existsByAccountNumber(createAccountRequest.accountNumber())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, " Account number already in use");
            }

            Customer customer  = customerRepository.findById(createAccountRequest.customerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

            Account account = new Account();
            account.setAccountNumber(createAccountRequest.accountNumber());
            account.setBalance(createAccountRequest.balance());
            account.setAccountType(createAccountRequest.accountType());
            account.setActCurrency(createAccountRequest.actCurrency());
            account.setIsDeleted(false);
            account.setCustomer(customer);
            accountRepository.save(account);
            return accountMapper.fromAccount(account) ;

        }

    @Override
        public List<AccountResponse> findAllAccounts() {
            return accountRepository
                    .findAll()
                    .stream()
                    .map(accountMapper::fromAccount).toList();
                    // account->accountMapper.fromAccount(account)
        }


    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        return accountMapper.fromAccount(account);
    }


    @Override
    public List<AccountResponse> findAccountByCustomerId(Integer customerId) {

        customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        List<Account> accounts = accountRepository.findAccountByCustomerId(customerId);
        return accounts
                .stream()
                .map(accountMapper::fromAccount).toList();
    }

    @Transactional
    @Override
    public void deleteAccountByAccountNumber(String accountNumber) {
        try {
            if (!accountRepository.existsByAccountNumber(accountNumber)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
            }
            accountRepository.deleteByAccountNumber(accountNumber);
        } catch (Exception e) {
            e.printStackTrace(); // Log the actual issue
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete account");
        }
    }

    @Override
    public AccountResponse updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        // instead of using many condition like above we can use mapstruct
        accountMapper.toAccountPartially(updateAccountRequest, account);

        account =  accountRepository.save(account);

        return accountMapper.fromAccount(account);
    }

    @Override
    public void disableAccountByAccountNumber(String accountNumber) {
        int updated = accountRepository.disableAccountByAccountNumber(accountNumber);
        if (updated == 0) {
            throw new EntityNotFoundException("Account not found with accountNumber: " + accountNumber);
        }
    }
}
