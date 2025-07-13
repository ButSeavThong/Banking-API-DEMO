package kh.coding.fullstackjpa.service;

import kh.coding.fullstackjpa.dto.CreateCustomerRequest;
import kh.coding.fullstackjpa.dto.CustomerResponse;
import kh.coding.fullstackjpa.dto.UpdateCustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService  {
    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse findCustomerByPhoneNumber(String phoneNumber);
    CustomerResponse updateByPhoneNumber(String phoneNumebr, UpdateCustomerRequest updateCustomerRequest);
}
