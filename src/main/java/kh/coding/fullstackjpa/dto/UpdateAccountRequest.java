package kh.coding.fullstackjpa.dto;

public record UpdateAccountRequest(
        Double balance,
        String actCurrency
) {
}
