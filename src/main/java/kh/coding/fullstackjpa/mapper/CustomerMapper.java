package kh.coding.fullstackjpa.mapper;


import kh.coding.fullstackjpa.domain.Customer;
import kh.coding.fullstackjpa.dto.CreateCustomerRequest;
import kh.coding.fullstackjpa.dto.CustomerResponse;
import kh.coding.fullstackjpa.dto.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);

    /**
     * DTO -> Model
     * Model -> DTO
     * return type is type that we want map to
     * parametr is source data ( data use to map )
     */

    CustomerResponse fromCustomer(Customer customer);

    Customer toCustomer(CustomerResponse customerResponse);

    Customer fromcreateRequestToCustomer(CreateCustomerRequest createCustomerRequest);

}
