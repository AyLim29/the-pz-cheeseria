package com.cheeseria.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.cheeseria.backend.model.Cheese;
import com.cheeseria.backend.repository.CheeseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(CheeseController.class)
public class CheeseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheeseRepository cheeseRepository;

    private Optional<Cheese> cheese1;
    private Optional<Cheese> cheese2;

    @BeforeEach
    void setUp() {
        cheese1 = Optional.ofNullable(Cheese.builder().id(6L).name("Fake Cheese").colour("Yellow")
		.pricePerKilo(10000.0).imageURL("https://upload.wikimedia.org/wikipedia/commons/1/1c/Single_wrapped_slice_of_processed_cheese.jpg").build());
        cheese2 = Optional.ofNullable(Cheese.builder().id(7L).name("Faker Cheese").colour("Yellower")
		.pricePerKilo(9999.0).imageURL("https://upload.wikimedia.org/wikipedia/commons/1/1c/Single_wrapped_slice_of_processed_cheese.jpg").build());
    }

    @Test
    public void testAddCheese() throws Exception{
		mockMvc.perform(post("/api/addCheese")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(cheese1.get())))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetCheeseById() throws Exception {
        when(cheeseRepository.findById(6L)).thenReturn(cheese1);
		mockMvc.perform(get("/api/getCheeseById/{id}", 6L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Fake Cheese"));
    }

    @Test
    public void testGetAllCheeses() throws Exception {
        List<Cheese> mockAllCheeses = Arrays.asList(cheese1.get(), cheese2.get());

        when(cheeseRepository.findAll()).thenReturn(mockAllCheeses);
		mockMvc.perform(get("/api/getAllCheeses")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(cheese1.get().getName()))
            .andExpect(jsonPath("$[1].name").value(cheese2.get().getName()));
    }

    @Test
    public void testUpdateCheeseById() throws Exception {
        Optional<Cheese> cheese1Updated = Optional.ofNullable(Cheese.builder().id(6L).name("Real Cheese").colour("Yellow")
		.pricePerKilo(10000.0).imageURL("https://upload.wikimedia.org/wikipedia/commons/1/1c/Single_wrapped_slice_of_processed_cheese.jpg").build());
        when(cheeseRepository.findById(6L)).thenReturn(cheese1);
        mockMvc.perform(put("/api/updateCheeseById/{id}", 6L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(cheese1Updated.get())))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCheeseById() throws Exception {
        doNothing().when(cheeseRepository).deleteById(6L);
		mockMvc.perform(delete("/api/deleteCheeseById/{id}", 6L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

}
