package com.pocketbook.pocketbookapi.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.pocketbook.pocketbookapi.model.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private LocalDateTime date;
    private String description;
    private Float amount;

    public static Item from(ItemDto e) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(e, Item.class);
    }

    public static ItemDto to(Item e) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(e, ItemDto.class);
    }

}
