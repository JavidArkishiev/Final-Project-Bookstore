package az.practice.bookstore.service;

import az.practice.bookstore.exception.ExistsEmailException;
import az.practice.bookstore.exception.ExistsPhoneNumberException;
import az.practice.bookstore.exception.UserNotFoundException;
import az.practice.bookstore.model.dto.AddressDto;
import az.practice.bookstore.model.dto.UserDto;
import az.practice.bookstore.model.entity.*;
import az.practice.bookstore.model.mapper.AddressMapper;
import az.practice.bookstore.model.mapper.UserMapper;
import az.practice.bookstore.repository.AddressRepository;
import az.practice.bookstore.repository.CartRepository;
import az.practice.bookstore.repository.OrderRepository;
import az.practice.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;


    public UserDto addUsers(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ExistsEmailException("this email already used " + userDto.getEmail());
        }
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new ExistsPhoneNumberException("this phone number already used");
        }
        Users usersEntity = userMapper.mapToUserEntity(userDto);
        Address addressEntity = addressMapper.mapToAddressEntity(userDto.getAddressDto());
        usersEntity.setAddress(addressEntity);
        addressEntity.setUsers(usersEntity);

        userRepository.save(usersEntity);
        return userDto;
    }

    public UserDto getById(Long id) {
        Users userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("This id not found :" + id));
//        AddressDto addressDto = addressMapper.mapToDto(userEntity.getAddress());
        UserDto userDto = userMapper.mapToUserDto(userEntity);
//        userDto.setAddressDto(addressDto);
        return userDto;
    }

    public List<UserDto> getAllUser() {
        List<Users> usersListEntity = userRepository.findAll();
        return usersListEntity.stream()
                .map(users -> {
                    UserDto userDto = userMapper.mapToUserDto(users);
                    AddressDto addressDto = addressMapper.mapToDto(users.getAddress());
                    userDto.setAddressDto(addressDto);
                    return userDto;

                }).collect(Collectors.toList());

    }

    public String deleteById(Long id) {
        Users usersEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("This id not found :" + id));
try {
    userRepository.delete(usersEntity);
}catch (StackOverflowError exception){
    exception.printStackTrace();
}
        return "Success";
    }


    public UserDto updateUser(Long id, UserDto requestDto) {
        Users oldUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("This id not found :" + id));
        if (oldUser != null) {
            Users updateUsers = userMapper.mapToUserEntity(requestDto);
            Address updateAddress = addressMapper.mapToAddressEntity(requestDto.getAddressDto());

            updateAddress.setId(oldUser.getAddress().getId());
            updateUsers.setId(oldUser.getId());

            updateUsers.setAddress(updateAddress);
            updateAddress.setUsers(updateUsers);
            userRepository.save(updateUsers);
            return requestDto;
        }
        return null;
    }

}
