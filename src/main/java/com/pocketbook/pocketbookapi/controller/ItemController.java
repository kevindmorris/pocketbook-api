package com.pocketbook.pocketbookapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pocketbook.pocketbookapi.dto.ItemDto;
import com.pocketbook.pocketbookapi.service.ItemService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Item Controller", description = "Manage item content.")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @PostMapping("/items")
    public ItemDto createItem(@RequestBody ItemDto e) throws Exception {
        log.info("POST /items");
        try {
            return itemService.create(e);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @GetMapping("/items")
    public List<ItemDto> getAllItems() throws Exception {
        log.info("GET /items");
        try {
            return itemService.getAll();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @GetMapping("/items/{id}")
    public ItemDto getItem(@PathVariable Long id) throws Exception {
        log.info(String.format("GET /items/%s", id));
        try {
            return itemService.get(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @PutMapping("/items/{id}")
    public ItemDto updateItem(@PathVariable Long id, @RequestBody ItemDto e) throws Exception {
        log.info(String.format("PUT /item/%s", id));
        try {
            return itemService.update(id, e);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/items")
    public void deleteAllItems() throws Exception {
        log.info("DELETE /items");
        try {
            itemService.deleteAll();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) throws Exception {
        log.info(String.format("DELETE /item/%s", id));
        try {
            itemService.delete(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

}
