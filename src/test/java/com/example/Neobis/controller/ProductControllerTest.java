package com.example.Neobis.controller;

import com.example.Neobis.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testSaveProduct() throws Exception {
        ProductDto productDto = new ProductDto(4L, "Кола", "500");

        mockMvc.perform(MockMvcRequestBuilders.post("/product/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testUpdateProduct() throws Exception {
        Long productId = 2L; //
        ProductDto productDto = new ProductDto(2L, "Пельмени", "500");

        mockMvc.perform(MockMvcRequestBuilders.put("/product/update/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testFindById() throws Exception {
        Long productId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.get("/product/findById")
                        .param("id", productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void testDeleteProduct() throws Exception {
        Long productId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete/{id}", productId))
                .andExpect(status().isOk());
    }
}
