package kh.coding.fullstackjpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAccountRequest(

        @NotBlank
        String accountNumber,

        @NotNull
        @Positive
        Double balance,

        @NotBlank
        String actCurrency,

        @NotNull
        Boolean isDeleted,

        @NotNull
        Integer customerId
) {}

