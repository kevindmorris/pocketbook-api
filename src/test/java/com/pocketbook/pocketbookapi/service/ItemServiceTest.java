package com.pocketbook.pocketbookapi.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pocketbook.pocketbookapi.dto.ItemDto;
import com.pocketbook.pocketbookapi.model.Item;
import com.pocketbook.pocketbookapi.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

        @Mock
        private ItemRepository itemRepository;

        @InjectMocks
        private ItemServiceImpl itemService;

        @Test
        void testCreate() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                Item item = Item.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                ItemDto itemDto = ItemDto.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

                // Act
                ItemDto savedItem = itemService.create(itemDto);

                // Assert
                Assertions.assertThat(savedItem).isNotNull();
                Assertions.assertThat(savedItem.getId()).isNotNull();
                Assertions.assertThat(savedItem.getDate()).isEqualTo(date);
                Assertions.assertThat(savedItem.getDescription()).isEqualTo(description);
                Assertions.assertThat(savedItem.getAmount()).isEqualTo(amount);
        }

        @Test
        void getAll() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense 1";
                Float amount = 10.00F;
                Long id2 = 2L;
                LocalDateTime date2 = LocalDateTime.now();
                String description2 = "Expense 2";
                Float amount2 = 20.00F;
                Item item = Item.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                Item item2 = Item.builder()
                                .id(id2)
                                .date(date2)
                                .description(description2)
                                .amount(amount2)
                                .build();
                List<Item> items = List.of(item, item2);
                when(itemRepository.findAll()).thenReturn(items);

                // Act
                List<ItemDto> retrievedItems = itemService.getAll();

                // Assert
                Assertions.assertThat(retrievedItems).isNotNull();
                Assertions.assertThat(retrievedItems.size()).isEqualTo(2);
        }

        @Test
        void testGet() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                Item item = Item.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(item));

                // Act
                ItemDto retrievedItem = itemService.get(item.getId());

                // Assert
                Assertions.assertThat(retrievedItem).isNotNull();
                Assertions.assertThat(retrievedItem.getId()).isNotNull();
                Assertions.assertThat(retrievedItem.getDate()).isEqualTo(date);
                Assertions.assertThat(retrievedItem.getDescription()).isEqualTo(description);
                Assertions.assertThat(retrievedItem.getAmount()).isEqualTo(amount);
        }

        @Test
        void testUpdate() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                String updatedDescription = "Updated Expense";
                Float updatedAmount = 20.00F;
                Item item = Item.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                Item alteredItem = Item.builder()
                                .id(id)
                                .date(date)
                                .description(updatedDescription)
                                .amount(updatedAmount)
                                .build();
                ItemDto itemDto = ItemDto.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(item));
                when(itemRepository.save(Mockito.any(Item.class))).thenReturn(alteredItem);

                // Act
                ItemDto updatedItem = itemService.update(item.getId(), itemDto);

                // Assert
                Assertions.assertThat(updatedItem).isNotNull();
                Assertions.assertThat(updatedItem.getId()).isNotNull();
                Assertions.assertThat(updatedItem.getDate()).isEqualTo(date);
                Assertions.assertThat(updatedItem.getDescription()).isEqualTo(updatedDescription);
                Assertions.assertThat(updatedItem.getAmount()).isEqualTo(updatedAmount);
        }

        @Test
        void testDeleteAll() throws Exception {
                // Arrange
                doNothing().when(itemRepository).deleteAll();

                // Act
                itemService.deleteAll();

                // Assert
                assertAll(() -> itemService.deleteAll());
        }

        @Test
        void testDelete() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                Item item = Item.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                doNothing().when(itemRepository).deleteById(item.getId());

                // Act
                itemService.delete(item.getId());

                // Assert
                assertAll(() -> itemService.delete(item.getId()));
        }
}
