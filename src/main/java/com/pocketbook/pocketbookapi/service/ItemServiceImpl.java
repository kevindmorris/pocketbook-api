package com.pocketbook.pocketbookapi.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocketbook.pocketbookapi.dto.ItemDto;
import com.pocketbook.pocketbookapi.model.Item;
import com.pocketbook.pocketbookapi.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ItemDto create(ItemDto e) throws Exception {
        Item response = itemRepository.save(ItemDto.from(e));
        return ItemDto.to(response);
    }

    @Override
    public List<ItemDto> getAll() throws Exception {
        return itemRepository.findAll().stream().map(ItemDto::to).collect(Collectors.toList());
    }

    @Override
    public ItemDto get(Long id) throws Exception {
        Item response = itemRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("cannot find item with id : %s", id)));
        return ItemDto.to(response);
    }

    @Override
    public ItemDto update(Long id, ItemDto e) throws Exception {
        Item response = itemRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("cannot find item with id : %s", id)));

        if (Objects.nonNull(e.getDate()))
            response.setDate(e.getDate());
        if (Objects.nonNull(e.getDescription()))
            response.setDescription(e.getDescription());
        if (Objects.nonNull(e.getAmount()))
            response.setAmount(e.getAmount());

        response = itemRepository.save(response);
        return ItemDto.to(response);
    }

    @Override
    public void deleteAll() throws Exception {
        itemRepository.deleteAll();
    }

    @Override
    public void delete(Long id) throws Exception {
        itemRepository.deleteById(id);
    }

}
