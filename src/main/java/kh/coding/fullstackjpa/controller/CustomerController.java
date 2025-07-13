package kh.coding.fullstackjpa.controller;


import jakarta.validation.Valid;
import kh.coding.fullstackjpa.dto.CreateCustomerRequest;
import kh.coding.fullstackjpa.dto.CustomerResponse;
import kh.coding.fullstackjpa.dto.UpdateCustomerRequest;
import kh.coding.fullstackjpa.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")

public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @GetMapping("/{phoneNumber}")
    public CustomerResponse findCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findCustomerByPhoneNumber(phoneNumber);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody
                                       CreateCustomerRequest
                                               createCustomerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.
                        createNew(createCustomerRequest));
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return customerService.getAllCustomers();
    }


    @PatchMapping("/{phoneNumber}")
    public CustomerResponse updateByPhoneNumber( @PathVariable String phoneNumber, @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateByPhoneNumber(phoneNumber, updateCustomerRequest);
    }

}
