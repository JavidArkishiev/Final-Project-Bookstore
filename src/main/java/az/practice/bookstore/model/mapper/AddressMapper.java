package az.practice.bookstore.model.mapper;

import az.practice.bookstore.model.dto.request.AddressDto;
import az.practice.bookstore.model.entity.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address mapToAddressEntity(AddressDto addressDto);

    AddressDto mapToDto(Address address);
}
