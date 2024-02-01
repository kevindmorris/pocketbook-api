package com.pocketbook.pocketbookapi.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDateTime;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocketbook.pocketbookapi.dto.ItemDto;
import com.pocketbook.pocketbookapi.service.ItemService;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ItemService itemService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testCreateItem() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense";
                Float amount = 10.00F;
                ItemDto itemDto = ItemDto.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                when(itemService.create(Mockito.any(ItemDto.class))).thenReturn(itemDto);

                // Act
                ResultActions response = mockMvc.perform(post("/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemDto)));

                // Assert
                response.andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(itemDto.getId()),
                                                Long.class))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                                CoreMatchers.is(itemDto.getDescription())))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",
                                                CoreMatchers.equalTo(itemDto.getAmount()),
                                                Float.class));
        }

        @Test
        void testGetAllItems() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense 1";
                Float amount = 10.00F;
                Long id2 = 2L;
                LocalDateTime date2 = LocalDateTime.now();
                String description2 = "Expense 2";
                Float amount2 = 20.00F;
                ItemDto itemDto = ItemDto.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                ItemDto itemDto2 = ItemDto.builder()
                                .id(id2)
                                .date(date2)
                                .description(description2)
                                .amount(amount2)
                                .build();
                List<ItemDto> itemDtos = List.of(itemDto, itemDto2);
                when(itemService.getAll()).thenReturn(itemDtos);

                // Act
                ResultActions response = mockMvc.perform(get("/items")
                                .contentType(MediaType.APPLICATION_JSON));

                // Assert
                response.andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                                                CoreMatchers.is(itemDtos.size())));
        }

        @Test
        void testGetItem() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense 1";
                Float amount = 10.00F;
                ItemDto itemDto = ItemDto.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                when(itemService.get(Mockito.anyLong())).thenReturn(itemDto);

                // Act
                ResultActions response = mockMvc.perform(get(String.format("/items/%s", itemDto.getId()))
                                .contentType(MediaType.APPLICATION_JSON));

                // Assert
                response.andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(itemDto.getId()),
                                                Long.class))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                                CoreMatchers.is(itemDto.getDescription())))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",
                                                CoreMatchers.equalTo(itemDto.getAmount()),
                                                Float.class));
        }

        @Test
        void testUpdateItem() throws Exception {
                // Arrange
                Long id = 1L;
                LocalDateTime date = LocalDateTime.now();
                String description = "Expense 1";
                Float amount = 10.00F;
                ItemDto itemDto = ItemDto.builder()
                                .id(id)
                                .date(date)
                                .description(description)
                                .amount(amount)
                                .build();
                when(itemService.update(Mockito.anyLong(), Mockito.any(ItemDto.class))).thenReturn(itemDto);

                // Act
                ResultActions response = mockMvc.perform(put(String.format("/items/%s", itemDto.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(itemDto)));

                // Assert
                response.andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(itemDto.getId()),
                                                Long.class))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                                CoreMatchers.is(itemDto.getDescription())))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",
                                                CoreMatchers.equalTo(itemDto.getAmount()),
                                                Float.class));

        }

        @Test
        void testDeleteAllItems() throws Exception {
                // Arrange
                doNothing().when(itemService).deleteAll();

                // Act
                ResultActions response = mockMvc.perform(delete("/items")
                                .contentType(MediaType.APPLICATION_JSON));

                // Assert
                response.andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void testDeleteItem() throws Exception {
                // Arrange
                Long id = 1L;
                doNothing().when(itemService).delete(id);

                // Act
                ResultActions response = mockMvc.perform(delete(String.format("/items/%s", id))
                                .contentType(MediaType.APPLICATION_JSON));

                // Assert
                response.andExpect(MockMvcResultMatchers.status().isOk());
        }
}
