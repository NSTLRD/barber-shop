/**
 * @author Starling Diaz on 10/6/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/6/2024.
 */

package com.mentorly.barber_shop.service;

import com.mentorly.barber_shop.dto.BarberDTO;

import java.util.List;

public interface BarberService {
    List<BarberDTO> getAllBarbers();
    BarberDTO getBarberById(Long id);
    BarberDTO updateBarber(Long id, BarberDTO barberDTO);
    BarberDTO createBarber(BarberDTO barberDTO);  // New method for creating a barber
    void deleteBarber(Long id);
}