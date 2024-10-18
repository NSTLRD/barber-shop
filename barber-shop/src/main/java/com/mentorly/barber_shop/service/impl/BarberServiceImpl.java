/**
 * @author Starling Diaz on 10/6/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/6/2024.
 */

package com.mentorly.barber_shop.service.impl;

import com.mentorly.barber_shop.dto.BarberDTO;
import com.mentorly.barber_shop.entity.Barber;
import com.mentorly.barber_shop.exception.BarberNotFoundException;
import com.mentorly.barber_shop.mapper.BarberMapper;
import com.mentorly.barber_shop.repository.BarberRepository;
import com.mentorly.barber_shop.service.BarberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BarberServiceImpl implements BarberService {

    private BarberRepository barberRepository;

    private BarberMapper barberMapper;

    @Override
    public List<BarberDTO> getAllBarbers() {
        return barberRepository.findAll()
                .stream()
                .map(barberMapper::barberToBarberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BarberDTO getBarberById(Long id) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberNotFoundException("Barber not found"));
        return barberMapper.barberToBarberDTO(barber);
    }

    @Override
    public BarberDTO updateBarber(Long id, BarberDTO barberDTO) {
        Barber existingBarber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberNotFoundException("Barber not found"));

        barberMapper.barberDTOToBarber(barberDTO); // Using mapper to map DTO to entity
        existingBarber.setName(barberDTO.getName());
        existingBarber.setExperience(barberDTO.getExperience());
        existingBarber.setSpecialty(barberDTO.getSpecialty());

        Barber updatedBarber = barberRepository.save(existingBarber);
        return barberMapper.barberToBarberDTO(updatedBarber);
    }

    @Override
    public BarberDTO createBarber(BarberDTO barberDTO) {
        Barber barber = barberMapper.barberDTOToBarber(barberDTO);  // Map DTO to entity
        Barber savedBarber = barberRepository.save(barber);         // Save entity to database
        return barberMapper.barberToBarberDTO(savedBarber);         // Map saved entity back to DTO
    }

    @Override
    public void deleteBarber(Long id) {
        barberRepository.deleteById(id);
    }
}