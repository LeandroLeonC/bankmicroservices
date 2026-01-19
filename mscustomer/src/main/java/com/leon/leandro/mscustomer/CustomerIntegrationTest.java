package com.leon.leandro.mscustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leon.leandro.mscustomer.dto.CustomerRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCustomerSuccessfully() throws Exception {

        CustomerRequestDto request = new CustomerRequestDto(
                "Leandro León",
                "M",
                32,
                "0106594385",
                "address",
                "2343434",
                "password",
                true
        );

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Leandro León"))
                .andExpect(jsonPath("$.identification").value("0106594385"))
                .andExpect(jsonPath("$.active").value(true));
    }
}
