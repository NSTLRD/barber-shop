package com.mentorly.barber_shop.mapper;

import com.mentorly.barber_shop.dto.BarberDTO;
import com.mentorly.barber_shop.entity.Barber;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-06T23:39:01-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class BarberMapperImpl implements BarberMapper {

    @Override
    public BarberDTO barberToBarberDTO(Barber barber) {
        if ( barber == null ) {
            return null;
        }

        BarberDTO.BarberDTOBuilder barberDTO = BarberDTO.builder();

        barberDTO.id( barber.getId() );
        barberDTO.name( barber.getName() );
        barberDTO.experience( barber.getExperience() );
        barberDTO.specialty( barber.getSpecialty() );

        return barberDTO.build();
    }

    @Override
    public Barber barberDTOToBarber(BarberDTO barberDTO) {
        if ( barberDTO == null ) {
            return null;
        }

        Barber barber = new Barber();

        barber.setId( barberDTO.getId() );
        barber.setName( barberDTO.getName() );
        barber.setExperience( barberDTO.getExperience() );
        barber.setSpecialty( barberDTO.getSpecialty() );

        return barber;
    }
}
