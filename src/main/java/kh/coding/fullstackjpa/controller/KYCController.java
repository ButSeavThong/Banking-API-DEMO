package kh.coding.fullstackjpa.controller;

import kh.coding.fullstackjpa.domain.KYC;
import kh.coding.fullstackjpa.repository.KYCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/kyc")
@RequiredArgsConstructor
public class KYCController {
    private final KYCRepository kycRepository;

    @PutMapping("/{customerId}/verify")
    public ResponseEntity<String> verifyKyc(@PathVariable Integer customerId) {

        KYC kyc = kycRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        kyc.setIsVerified(true);
        kycRepository.save(kyc);
        return ResponseEntity.ok("KYC verified");
    }
}

