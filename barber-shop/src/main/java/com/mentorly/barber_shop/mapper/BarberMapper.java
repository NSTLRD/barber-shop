/**
 * @author Starling Diaz on 10/6/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/6/2024.
 */

package com.mentorly.barber_shop.mapper;

import com.mentorly.barber_shop.dto.BarberDTO;
import com.mentorly.barber_shop.entity.Barber;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BarberMapper {
    BarberMapper INSTANCE = Mappers.getMapper(BarberMapper.class);

    BarberDTO barberToBarberDTO(Barber barber);
    Barber barberDTOToBarber(BarberDTO barberDTO);
}