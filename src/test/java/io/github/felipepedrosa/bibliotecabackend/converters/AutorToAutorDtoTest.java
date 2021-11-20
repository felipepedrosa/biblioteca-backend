package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AutorToAutorDtoTest {

    private AutorToAutorDto autorToAutorDto;

    @BeforeEach
    void setUp() {
        autorToAutorDto = new AutorToAutorDto();
    }

    @Test
    @DisplayName("Should return null when trying to convert a null value to AutorDto")
    public void testNullConvert() {
        AutorDto dto = autorToAutorDto.convert(null);
        assertNull(dto);
    }

    @Test
    @DisplayName("Should convert an Autor with all properties to AutorDto")
    public void testSuccessfullyConvert() {
        Autor autor = new Autor();
        autor.setId(UUID.randomUUID());
        autor.setNome("Autor 1");

        AutorDto dto = autorToAutorDto.convert(autor);

        assertNotNull(dto);
        assertEquals(autor.getId(), dto.getId());
        assertEquals(autor.getNome(), dto.getNome());
    }

}