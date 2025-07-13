package kh.coding.fullstackjpa.service;

import kh.coding.fullstackjpa.dto.AccountResponse;
import kh.coding.fullstackjpa.dto.CreateAccountRequest;
import kh.coding.fullstackjpa.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createNewAccount(CreateAccountRequest request);

    List<AccountResponse> findAllAccounts();

    AccountResponse findAccountByAccountNumber(String accountNumber);

    List<AccountResponse> findAccountByCustomerId(Integer customerId);
    
    void deleteAccountByAccountNumber(String accountNumber);

    AccountResponse updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest);

}