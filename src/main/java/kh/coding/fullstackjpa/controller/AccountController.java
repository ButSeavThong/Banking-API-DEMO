package kh.coding.fullstackjpa.controller;


import jakarta.validation.Valid;
import kh.coding.fullstackjpa.dto.AccountResponse;
import kh.coding.fullstackjpa.dto.CreateAccountRequest;
import kh.coding.fullstackjpa.dto.UpdateAccountRequest;
import kh.coding.fullstackjpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createNewAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createNewAccount(createAccountRequest));
    }

    @GetMapping
    public List<AccountResponse> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse findAccountByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findAccountByAccountNumber(accountNumber);
    }


    @GetMapping("/{customer}/{customerId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByCustomer(@PathVariable Integer customerId){
        return ResponseEntity.ok(accountService.findAccountByCustomerId(customerId));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccountByAccountNumber(@PathVariable String accountNumber) {
        accountService.deleteAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{accountNumber}")
    public AccountResponse updateAccountByAccountNumber(@PathVariable  String accountNumber, @RequestBody UpdateAccountRequest updateAccountRequest) {
        return accountService.updateAccountByAccountNumber(accountNumber,updateAccountRequest);
    }

}
