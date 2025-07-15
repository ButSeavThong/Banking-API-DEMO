package kh.coding.fullstackjpa.service.impl;

import jakarta.transaction.Transactional;
import kh.coding.fullstackjpa.domain.Customer;

import kh.coding.fullstackjpa.domain.Segment;
import kh.coding.fullstackjpa.dto.CreateCustomerRequest;
import kh.coding.fullstackjpa.dto.UpdateCustomerRequest;
import kh.coding.fullstackjpa.dto.CustomerResponse;
import kh.coding.fullstackjpa.mapper.CustomerMapper;
import kh.coding.fullstackjpa.repository.CustomerRepository;
import kh.coding.fullstackjpa.repository.SegmentRepository;
import kh.coding.fullstackjpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    // put have to validation
    // patch don't have to validate before upate
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final SegmentRepository segmentRepository;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {

        if(!customerRepository.isExistsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);

    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {
        // validate email
        if(customerRepository.existsByEmail(createCustomerRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        // Phone number
        if(customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone already in use");
        }

        // national card
        if(customerRepository.existsByNationalCardId(createCustomerRequest.nationalCardId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "National Card ID not unique");
        }

        Customer customer = customerMapper.fromcreateRequestToCustomer(createCustomerRequest);
        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());

        // assign segment to customer
        List<Segment> segments = new ArrayList<>();
        Segment segment =  segmentRepository.findBySegment("Regular")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment not found"));
        segments.add(segment);

        // add segment to cus
        customer.setSegment(segment);

        customer = customerRepository.save(customer);

        return  customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findCustomerByIsDeletedFalse()
                .stream()
                // using mapstruct instead of
                .map(customerMapper::fromCustomer).toList();
    }


    @Override
    public CustomerResponse findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findCustomerByPhoneNumber(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number Not found"));
    }


    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumebr, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository
                .findCustomerByPhoneNumber(phoneNumebr)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number Not found"));

        // call method that map value input from controller to be entity
        // it like we set value to it by validation
        customerMapper.toCustomerPartially(updateCustomerRequest, customer);

        // after set we have to save that data again with old id
        customer = customerRepository.save(customer);

        // after save we have to return response
        return customerMapper.fromCustomer(customer);
    }



}
