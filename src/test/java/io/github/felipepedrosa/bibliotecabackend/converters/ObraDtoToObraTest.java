package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ObraDtoToObraTest {

    private ObraDtoToObra obraDtoToObra;

    @BeforeEach
    void setUp() {
        obraDtoToObra = new ObraDtoToObra(new AutorDtoToAutor());
    }

    @Test
    @DisplayName("Should return null when trying to convert a null value to Obra")
    public void testNullConvert() {
        Obra obra = obraDtoToObra.convert(null);
        assertNull(obra);
    }

    @Test
    @DisplayName("Should convert an ObraDto with all properties to Obra")
    public void testSuccessfullyConvert() {
        ObraDto dto = new ObraDto();
        dto.setId(UUID.randomUUID());
        dto.setTitulo("Titulo");
        dto.setEditora("Editora");
        dto.setUrlImagem("localhos:8080/imgs/teste.png");

        AutorDto autorDto1 = new AutorDto();
        autorDto1.setId(UUID.randomUUID());
        autorDto1.setNome("Autor 1");

        AutorDto autorDto2 = new AutorDto();
        autorDto2.setId(UUID.randomUUID());
        autorDto2.setNome("Autor 2");

        dto.setAutores(Set.of(autorDto1, autorDto2));

        Obra obra = obraDtoToObra.convert(dto);

        assertNotNull(obra);
        assertEquals(dto.getId(), obra.getId());
        assertEquals(dto.getTitulo(), obra.getTitulo());
        assertEquals(dto.getEditora(), obra.getEditora());
        assertEquals(dto.getUrlImagem(), obra.getUrlImagem());
        assertEquals(dto.getAutores().size(), obra.getAutores().size());
    }

}