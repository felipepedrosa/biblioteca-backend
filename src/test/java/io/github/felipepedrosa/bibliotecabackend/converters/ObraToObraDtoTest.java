package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ObraToObraDtoTest {

    private ObraToObraDto obraToObraDto;

    @BeforeEach
    void setUp() {
        obraToObraDto = new ObraToObraDto(new AutorToAutorDto());
    }

    @Test
    @DisplayName("Should return null when trying to convert a null value to Obra")
    public void testNullConvert() {
        ObraDto dto = obraToObraDto.convert(null);
        assertNull(dto);
    }

    @Test
    @DisplayName("Should convert an ObraDto with all properties to Obra")
    public void testSuccessfullyConvert() {
        Obra obra = new Obra();
        obra.setId(UUID.randomUUID());
        obra.setTitulo("Titulo");
        obra.setEditora("Editora");
        obra.setUrlImagem("localhos:8080/imgs/teste.png");

        Autor autor1 = new Autor();
        autor1.setId(UUID.randomUUID());
        autor1.setNome("Autor 1");

        Autor autor2 = new Autor();
        autor2.setId(UUID.randomUUID());
        autor2.setNome("Autor 2");

        obra.addAutores(Set.of(autor1, autor2));

        ObraDto dto = obraToObraDto.convert(obra);

        assertNotNull(dto);
        assertEquals(obra.getId(), dto.getId());
        assertEquals(obra.getTitulo(), dto.getTitulo());
        assertEquals(obra.getEditora(), dto.getEditora());
        assertEquals(obra.getUrlImagem(), dto.getUrlImagem());
        assertEquals(obra.getAutores().size(), dto.getAutores().size());
    }

}