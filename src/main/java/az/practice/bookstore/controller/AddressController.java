package az.practice.bookstore.controller;

import az.practice.bookstore.model.dto.request.AddressDto;
import az.practice.bookstore.service.AddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<AddressDto> createAddress(@RequestParam Long userId,
                                                    @RequestBody AddressDto addressDto) {
        return new ResponseEntity<>(addressService.createAddress(userId, addressDto), HttpStatus.CREATED);

    }
}
