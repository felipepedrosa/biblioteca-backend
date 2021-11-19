package io.github.felipepedrosa.bibliotecabackend.services;

import io.github.felipepedrosa.bibliotecabackend.converters.AutorDtoToAutor;
import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import io.github.felipepedrosa.bibliotecabackend.repositories.AutorRepository;
import io.github.felipepedrosa.bibliotecabackend.utils.AutorDtoUtils;
import io.github.felipepedrosa.bibliotecabackend.utils.AutorUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AutorDtoToAutor autorDtoToAutor;

    @InjectMocks
    private AutorServiceImpl autorService;

    @Test
    @DisplayName("Should create a new Autor")
    void testCreateSingle() {
        Autor autor = AutorUtils.createAutor();
        given(autorDtoToAutor.convert(any())).willReturn(autor);
        given(autorRepository.save(any())).willReturn(autor);

        AutorDto autorDto = AutorDtoUtils.createAutor();
        Autor result = autorService.create(autorDto);

        assertNotNull(result);
        assertEquals(autor.getId(), result.getId());
        assertEquals(autor.getNome(), result.getNome());
        assertEquals(autor.getObras().size(), result.getObras().size());
    }

    @Test
    @DisplayName("Should create multiple Autor")
    void testCreateMultiple() {
        Set<Autor> expected = AutorUtils.createAutors(2);
        Autor autor1 = CollectionUtils.firstElement(expected);
        Autor autor2 = CollectionUtils.lastElement(expected);

        given(autorDtoToAutor.convert(any()))
                .willReturn(autor1)
                .willReturn(autor2);

        given(autorRepository.save(any()))
                .willReturn(autor1)
                .willReturn(autor2);

        Set<AutorDto> dtosAutores = AutorDtoUtils.createAutors(2);
        Set<Autor> actual = autorService.create(dtosAutores);

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertThat(actual).hasSameElementsAs(expected);
    }
}