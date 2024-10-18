/**
 * @author Starling Diaz on 10/5/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/5/2024.
 */

package com.mentorly.barber_shop.service.impl;


import com.mentorly.barber_shop.constants.EmailTemplateName;
import com.mentorly.barber_shop.dto.LoginDTO;
import com.mentorly.barber_shop.dto.UserDTO;
import com.mentorly.barber_shop.dto.response.LoginResponseDTO;
import com.mentorly.barber_shop.entity.Token;
import com.mentorly.barber_shop.entity.User;
import com.mentorly.barber_shop.exception.TokenExpiredException;
import com.mentorly.barber_shop.exception.UserAlreadyExistsException;
import com.mentorly.barber_shop.exception.UserNotFoundException;
import com.mentorly.barber_shop.mapper.UserMapper;
import com.mentorly.barber_shop.repository.TokenRepository;
import com.mentorly.barber_shop.repository.UserRepository;
import com.mentorly.barber_shop.security.JwTServiceSecurity;
import com.mentorly.barber_shop.service.UserService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailServiceImpl emailServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JwTServiceSecurity jwTServiceSecurity;

    @Value("${mailing.frontend.activation.activationUrl}")
    private String activationUrl;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws Exception {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User with email: " + userDTO.getEmail() + " already exists");
        }
        // Map DTO to entity
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false); // Account is not enabled until activation

        // Save user in the database
        userRepository.save(user);

        //send the verification email
        sendVerificationEmail(user);

        return UserMapper.INSTANCE.userToUserDTO(user);
    }


    private void sendVerificationEmail(User user) throws MessagingException {
        //generate new token
        var newToken = generateAndSaveActivationToken(user);
        user.setToken(newToken);
        userRepository.save(user);
        //send email
        emailServiceImpl.sendEmail(
                user.getEmail(),
                user.FullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Activate your account"
        );

    }

    @Override
    public String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(45))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    @Override
    public String generateActivationCode(int length) {
        String characters = "123456789";
        StringBuilder result = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }
        return result.toString();
    }


    @Override
    public LoginResponseDTO loginAuthenticate(LoginDTO loginDto) {
        if(loginDto.getEmail() == null || loginDto.getEmail().isEmpty()){
            throw new RuntimeException("Email is required");
        }

        if(loginDto.getPassword() == null || loginDto.getPassword().isEmpty()){
            throw new RuntimeException("Password is required");
        }

        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            var user = (User) auth.getPrincipal();
            var claims = new HashMap<String, Object>();
            claims.put("name", user.getName());
            claims.put("email", user.getEmail());
            claims.put("id", user.getId());
            var jwtToken = jwTServiceSecurity.generateToken(claims, user);
            return LoginResponseDTO.builder().token(jwtToken).build();
        } catch (AuthenticationException e){
            if(userRepository.findByEmail(loginDto.getEmail()).isPresent()){
                throw new RuntimeException("Password is incorrect");
            } else {
                throw new UserNotFoundException("No user found with the provided email address");
            }
        }
    }

    @Override
    public String activateAccount(String token) throws MessagingException, TokenExpiredException {
        Optional<Token> tokenOptional = Optional.ofNullable(tokenRepository.findByToken(token));
        if(!tokenOptional.isPresent()){
            throw new TokenExpiredException("Invalid Token");
        }

        Token savedToken = tokenOptional.get();
        if(LocalDateTime.now().isAfter(savedToken.getExpiredAt())){
            sendVerificationEmail(savedToken.getUser());
            throw new TokenExpiredException("Activation token has expired. A new token has been sent to the email address.");
        }

        User user = savedToken.getUser();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
        return token;
    }
}
