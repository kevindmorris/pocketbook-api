package com.pocketbook.pocketbookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocketbook.pocketbookapi.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}