package io.github.felipepedrosa.bibliotecabackend.services;

import io.github.felipepedrosa.bibliotecabackend.converters.ObraDtoToObra;
import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import io.github.felipepedrosa.bibliotecabackend.repositories.ObraRepository;
import io.github.felipepedrosa.bibliotecabackend.utils.AutorDtoUtils;
import io.github.felipepedrosa.bibliotecabackend.utils.AutorUtils;
import io.github.felipepedrosa.bibliotecabackend.utils.ObraDtoUtils;
import io.github.felipepedrosa.bibliotecabackend.utils.ObraUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ObraServiceImplTest {
    @Mock
    private ObraRepository obraRepository;

    @Mock
    private ObraDtoToObra obraDtoToObra;

    @Mock
    private AutorService autorService;

    @InjectMocks
    private ObraServiceImpl obraService;

    @Test
    @DisplayName("Should create a new Obra using mocks")
    public void createWithoutErrors() {
        Obra expected = ObraUtils.createObraWithoutAutor(true);
        expected.addAutores(AutorUtils.createAutors(2, true));

        given(obraDtoToObra.convert(Mockito.any())).willReturn(expected);
        given(obraRepository.save(Mockito.any())).willReturn(expected);


        ObraDto obraDto = ObraDtoUtils.createObraWithoutAutor(true);
        obraDto.setAutores(AutorDtoUtils.createAutors(2, true));
        Obra returned = obraService.create(obraDto);

        assertEquals(expected.getId(), returned.getId());
        assertEquals(expected.getTitulo(), returned.getTitulo());
        assertEquals(expected.getEditora(), returned.getEditora());
        assertEquals(expected.getUrlImagem(), returned.getUrlImagem());
        assertEquals(expected.getAutores().size(), returned.getAutores().size());
    }

}
