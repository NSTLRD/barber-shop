package com.mentorly.barber_shop.mapper;

import com.mentorly.barber_shop.dto.UserDTO;
import com.mentorly.barber_shop.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T16:29:58-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setDni( user.getDni() );
        userDTO.setProfession( user.getProfession() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setCountry( user.getCountry() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setLastName( userDTO.getLastName() );
        user.setDni( userDTO.getDni() );
        user.setProfession( userDTO.getProfession() );
        user.setAddress( userDTO.getAddress() );
        user.setCountry( userDTO.getCountry() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );

        return user;
    }
}
