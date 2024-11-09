package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/user-details")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUserDetails(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
            @RequestParam(required = false) String email) {
        List<UserDto> userDetails = userService.getUserDetails(id, firstName, lastName, birthdate, email);
        return ResponseEntity.ok(userDetails);
    }
}
