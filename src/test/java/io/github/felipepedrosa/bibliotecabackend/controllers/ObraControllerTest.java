package io.github.felipepedrosa.bibliotecabackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import io.github.felipepedrosa.bibliotecabackend.services.ObraService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ObraController.class)
@ExtendWith(MockitoExtension.class)
class ObraControllerTest {

    @MockBean
    private ObraService obraService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should grant success on perform a post")
    public void testCreate() throws Exception {
        final Obra expected = new Obra();
        expected.setId(UUID.randomUUID());
        given(obraService.create(Mockito.any())).willReturn(expected);

        ObraDto requestDto = new ObraDto();
        requestDto.setTitulo("Titulo 1");
        requestDto.setEditora("Editora 1");
        requestDto.setUrlImagem("http://localhost/img.png");

        AutorDto autor1 = new AutorDto();
        autor1.setNome("Autor 1");

        AutorDto autor2 = new AutorDto();
        autor2.setNome("Autor 2");

        requestDto.setAutores(Set.of(autor1, autor2));

        mockMvc.perform(post("/obras")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpectAll(
                        status().isCreated(),
                        header().string("Location", "/obras/" + expected.getId().toString())
                );
    }

    @Test
    @DisplayName("Should return an error when trying to perform a post without body")
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