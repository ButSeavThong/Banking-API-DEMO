package kh.coding.fullstackjpa.dto;


public record UpdateCustomerRequest (
        String fullName,
        String gender,
        String remark
) {
}
