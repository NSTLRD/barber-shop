/**
 * @author Starling Diaz on 10/6/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/6/2024.
 */

package com.mentorly.barber_shop.service;

import com.mentorly.barber_shop.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(Long id);
    ItemDTO updateItem(Long id, ItemDTO itemDTO);
    ItemDTO createItem(ItemDTO itemDTO);  // New method for creating an item
    void deleteItem(Long id);
}
