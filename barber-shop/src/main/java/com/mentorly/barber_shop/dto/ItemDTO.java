/**
 * @author Starling Diaz on 10/6/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/6/2024.
 */

package com.mentorly.barber_shop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String type;
    private double price;
}
