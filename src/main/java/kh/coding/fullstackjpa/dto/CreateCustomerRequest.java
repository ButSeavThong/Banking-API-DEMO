package kh.coding.fullstackjpa.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank(message = "required")
        String fullName,

        @NotBlank(message = "required")
        String gender,
        String email,
        String phone,
        String remark
) {
}
