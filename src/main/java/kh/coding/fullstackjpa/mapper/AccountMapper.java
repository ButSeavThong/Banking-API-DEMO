package kh.coding.fullstackjpa.mapper;

import kh.coding.fullstackjpa.domain.Account;
import kh.coding.fullstackjpa.dto.AccountResponse;
import kh.coding.fullstackjpa.dto.CreateAccountRequest;
import kh.coding.fullstackjpa.dto.UpdateAccountRequest;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    AccountResponse fromAccount(Account account);

    Account toAccount(AccountResponse accountResponse);

    Account fromCreateRequestToAccount(CreateAccountRequest createAccountRequest);


}
