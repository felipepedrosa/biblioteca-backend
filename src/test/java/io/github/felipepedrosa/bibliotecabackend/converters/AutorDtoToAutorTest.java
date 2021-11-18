package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AutorDtoToAutorTest {

    private AutorDtoToAutor autorDtoToAutor;

    @BeforeEach
    void setUp() {
        autorDtoToAutor = new AutorDtoToAutor();
    }

    @Test
    @DisplayName("Should return null when trying to convert a null value to Autor")
    public void testNullConvert() {
        Autor autor = autorDtoToAutor.convert(null);
        assertNull(autor);
    }

    @Test
    @DisplayName("Should convert an AutorDto with all properties to Autor")
    public void testSuccessfullyConvert() {
        AutorDto dto = new AutorDto();
        dto.setId(UUID.randomUUID());
        dto.setNome("Autor 1");

        Autor autor = autorDtoToAutor.convert(dto);

        assertNotNull(autor);
        assertEquals(dto.getId(), autor.getId());
        assertEquals(dto.getNome(), autor.getNome());
    }

}