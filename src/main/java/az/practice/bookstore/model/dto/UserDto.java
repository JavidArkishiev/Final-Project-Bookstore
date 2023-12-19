package az.practice.bookstore.model.dto;

import az.practice.bookstore.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "firstName can not be null")
    private String firstName;
    @NotBlank(message = "lastName can not be null")
    private String lastName;
    @NotBlank(message = "email can not be null")
    @Pattern(regexp = "[\\w.-]+@[\\w.-]+.\\w+$")
    private String email;
    private String password;
    @NotBlank(message = "phoneNumber can not be null")
    @Pattern(regexp = "[0-9]{3}+[0-9]{3}+[0-9]{2}+[0-9]{2}")
    private String phoneNumber;
    private AddressDto addressDto;
    private Role role;


}
