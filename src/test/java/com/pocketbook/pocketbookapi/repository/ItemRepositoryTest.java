package com.pocketbook.pocketbookapi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pocketbook.pocketbookapi.model.Item;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ItemRepositoryTest {

        @Autowired
        private ItemRepository itemRepository;

        @Test
        public void testSave() {
                // Arrange
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                Item item = Item.builder()
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();

                // Act
                Item savedItem = itemRepository.save(item);

                // Assert
                Assertions.assertThat(savedItem).isNotNull();
                Assertions.assertThat(savedItem.getId()).isNotNull();
                Assertions.assertThat(savedItem.getDate()).isEqualTo(date);
                Assertions.assertThat(savedItem.getDescription()).isEqualTo(description);
                Assertions.assertThat(savedItem.getAmount()).isEqualTo(amount);
        }

        @Test
        public void testFindAll() {
                // Arrange
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense 1";
                Float amount = 10.00F;
                LocalDateTime date2 = LocalDateTime.now();
                String description2 = "Expense 2";
                Float amount2 = 20.00F;
                Item item = Item.builder()
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                Item item2 = Item.builder()
                                .date(date2)
                                .description(description2)
                                .amount(amount2)
                                .build();

                // Act
                itemRepository.save(item);
                itemRepository.save(item2);
                List<Item> retrievedItems = itemRepository.findAll();

                // Assert
                Assertions.assertThat(retrievedItems).isNotNull();
                Assertions.assertThat(retrievedItems.size()).isEqualTo(2);
        }

        @Test
        public void testFindById() {
                // Arrange
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                Item item = Item.builder()
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();

                // Act
                Item savedItem = itemRepository.save(item);
                Item retrievedItem = itemRepository.findById(item.getId()).get();

                // Assert
                Assertions.assertThat(retrievedItem).isNotNull();
                Assertions.assertThat(retrievedItem.getId()).isEqualTo(savedItem.getId());
                Assertions.assertThat(retrievedItem.getDate()).isEqualTo(date);
                Assertions.assertThat(retrievedItem.getDescription()).isEqualTo(description);
                Assertions.assertThat(retrievedItem.getAmount()).isEqualTo(amount);
        }

        @Test
        public void testDeleteAll() {
                // Arrange
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense 1";
                Float amount = 10.00F;
                LocalDateTime date2 = LocalDateTime.now();
                String description2 = "Expense 2";
                Float amount2 = 20.00F;
                Item item = Item.builder()
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                Item item2 = Item.builder()
                                .date(date2)
                                .description(description2)
                                .amount(amount2)
                                .build();

                // Act
                itemRepository.save(item);
                itemRepository.save(item2);
                itemRepository.deleteAll();
                List<Item> retrievedItems = itemRepository.findAll();

                // Assert
                Assertions.assertThat(retrievedItems).isNotNull();
                Assertions.assertThat(retrievedItems.size()).isEqualTo(0);
        }

        @Test
        public void testDeleteById() {
                // Arrange
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                Item item = Item.builder()
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();

                // Act
                Item savedItem = itemRepository.save(item);
                itemRepository.deleteById(savedItem.getId());
                Optional<Item> retrievedItem = itemRepository.findById(savedItem.getId());

                // Assert
                Assertions.assertThat(retrievedItem).isEmpty();
        }

}
