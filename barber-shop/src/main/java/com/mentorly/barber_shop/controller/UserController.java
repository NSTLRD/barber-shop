/**
 * @author Starling Diaz on 10/5/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/5/2024.
 */

package com.mentorly.barber_shop.controller;

import com.mentorly.barber_shop.dto.LoginDTO;
import com.mentorly.barber_shop.dto.UserDTO;
import com.mentorly.barber_shop.dto.response.LoginResponseDTO;
import com.mentorly.barber_shop.exception.IncorrectPasswordException;
import com.mentorly.barber_shop.exception.TokenExpiredException;
import com.mentorly.barber_shop.exception.UserNotFoundException;
import com.mentorly.barber_shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "Manage user accounts")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user and provides a JWT for authentication.")
    @ApiResponse(responseCode = "201", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Email already registered")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDto) throws Exception {
        UserDTO userResponse = userService.registerUser(userDto);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user and issues a JWT.")
    @ApiResponse(responseCode = "200", description = "User logged in successfully")
    @ApiResponse(responseCode = "401", description = "Incorrect credentials")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDto) {
        try {
            LoginResponseDTO loginResponse = userService.loginAuthenticate(loginDto);
            return ResponseEntity.ok(loginResponse);
        } catch (UserNotFoundException | IncorrectPasswordException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Internal Server Error", ex);
        }
    }

    @GetMapping("/activate-account")
    @Operation(summary = "Activate account", description = "Activate user account")
    @ApiResponse(responseCode = "200", description = "Account activated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<?> activateAccount(@RequestParam String token) throws TokenExpiredException, MessagingException {
        userService.activateAccount(token);
        return ResponseEntity.ok("Account activated successfully");
    }


}
