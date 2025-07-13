package kh.coding.fullstackjpa.dto;

public record UpdateAccountRequest(
        Double balance,
        String accountType,
        String actCurrency
) {
}
