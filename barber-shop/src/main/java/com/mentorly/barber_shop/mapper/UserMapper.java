/**
 * @author Starling Diaz on 10/4/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/4/2024.
 */

package com.mentorly.barber_shop.mapper;

import com.mentorly.barber_shop.dto.UserDTO;
import com.mentorly.barber_shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}