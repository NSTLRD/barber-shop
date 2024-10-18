package com.mentorly.barber_shop.mapper;

import com.mentorly.barber_shop.dto.ItemDTO;
import com.mentorly.barber_shop.entity.Item;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-06T23:39:01-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDTO itemToItemDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO.ItemDTOBuilder itemDTO = ItemDTO.builder();

        itemDTO.id( item.getId() );
        itemDTO.name( item.getName() );
        itemDTO.type( item.getType() );
        itemDTO.price( item.getPrice() );

        return itemDTO.build();
    }

    @Override
    public Item itemDTOToItem(ItemDTO itemDTO) {
        if ( itemDTO == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( itemDTO.getId() );
        item.setName( itemDTO.getName() );
        item.setType( itemDTO.getType() );
        item.setPrice( itemDTO.getPrice() );

        return item;
    }
}
