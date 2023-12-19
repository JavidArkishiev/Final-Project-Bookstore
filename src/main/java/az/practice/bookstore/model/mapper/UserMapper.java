package az.practice.bookstore.model.mapper;

import az.practice.bookstore.model.dto.UserDto;
import az.practice.bookstore.model.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    Users mapToUserEntity(UserDto userDto);



    @Mapping(target = "addressDto", source = "address")
    UserDto mapToUserDto(Users users);

    List<UserDto> mapToUserDtoList(List<Users> usersListEntity);
}
