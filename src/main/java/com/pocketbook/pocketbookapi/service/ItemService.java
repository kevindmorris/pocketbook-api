package com.pocketbook.pocketbookapi.service;

import java.util.List;

import com.pocketbook.pocketbookapi.dto.ItemDto;

public interface ItemService {

    public ItemDto create(ItemDto e) throws Exception;

    public List<ItemDto> getAll() throws Exception;

    public ItemDto get(Long id) throws Exception;

    public ItemDto update(Long id, ItemDto e) throws Exception;

    public void deleteAll() throws Exception;

    public void delete(Long id) throws Exception;

}
