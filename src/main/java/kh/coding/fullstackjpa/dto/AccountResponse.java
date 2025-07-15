package kh.coding.fullstackjpa.dto;

public record AccountResponse (
    String accountNumber,
    Double balance,
    String actCurrency
){
}
