/**
 * @author Starling Diaz on 10/6/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/6/2024.
 */

package com.mentorly.barber_shop.service.impl;

import com.mentorly.barber_shop.dto.ItemDTO;
import com.mentorly.barber_shop.entity.Item;
import com.mentorly.barber_shop.exception.ItemNotFoundException;
import com.mentorly.barber_shop.mapper.ItemMapper;
import com.mentorly.barber_shop.repository.ItemRepository;
import com.mentorly.barber_shop.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    private ItemMapper itemMapper;

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::itemToItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        return itemMapper.itemToItemDTO(item);
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));

        itemMapper.itemDTOToItem(itemDTO); // Using mapper to map DTO to entity
        existingItem.setName(itemDTO.getName());
        existingItem.setType(itemDTO.getType());
        existingItem.setPrice(itemDTO.getPrice());

        Item updatedItem = itemRepository.save(existingItem);
        return itemMapper.itemToItemDTO(updatedItem);
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = itemMapper.itemDTOToItem(itemDTO);   // Map DTO to entity
        Item savedItem = itemRepository.save(item);      // Save entity to database
        return itemMapper.itemToItemDTO(savedItem);      // Map saved entity back to DTO
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}