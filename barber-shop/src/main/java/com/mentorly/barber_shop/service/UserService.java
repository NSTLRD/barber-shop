/**
 * @author Starling Diaz on 10/5/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/5/2024.
 */

package com.mentorly.barber_shop.service;

import com.mentorly.barber_shop.dto.LoginDTO;
import com.mentorly.barber_shop.dto.UserDTO;
import com.mentorly.barber_shop.dto.response.LoginResponseDTO;
import com.mentorly.barber_shop.entity.User;
import com.mentorly.barber_shop.exception.TokenExpiredException;
import jakarta.mail.MessagingException;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO) throws Exception;
    String generateAndSaveActivationToken(User user);
    String generateActivationCode(int length);
    LoginResponseDTO loginAuthenticate(LoginDTO loginDto);
    String activateAccount(String token) throws MessagingException, TokenExpiredException;
}
