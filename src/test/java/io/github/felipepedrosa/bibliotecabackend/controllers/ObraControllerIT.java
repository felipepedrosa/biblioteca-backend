package io.github.felipepedrosa.bibliotecabackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.utils.AutorDtoUtils;
import io.github.felipepedrosa.bibliotecabackend.utils.ObraDtoUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ObraControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return code 201 and location for resource")
    public void testCreate() throws Exception {
        ObraDto requestDto = ObraDtoUtils.createObraWithoutAutor(false);
        requestDto.setAutores(AutorDtoUtils.createAutors(2, false));

        mockMvc.perform(post("/obras")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpectAll(
                        status().isCreated(),
                        header().exists("Location")
                );
    }

    @Test
    @DisplayName("Should return 400 with validation error message")
    public void testCreateWithValidationErrors() throws Exception {
        mockMvc.perform(post("/obras")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ObraDto())))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.timestamp", Matchers.notNullValue()),
                        jsonPath("$.httpStatus", Matchers.is(400)),
                        jsonPath("$.path", Matchers.notNullValue()),
                        jsonPath("$.messages", Matchers.notNullValue()),
                        jsonPath("$.description", Matchers.is("Your request have some validation issues!"))
                );
    }

}