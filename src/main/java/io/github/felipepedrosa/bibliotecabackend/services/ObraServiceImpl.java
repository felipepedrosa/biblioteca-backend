package io.github.felipepedrosa.bibliotecabackend.services;

import io.github.felipepedrosa.bibliotecabackend.converters.ObraDtoToObra;
import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import io.github.felipepedrosa.bibliotecabackend.repositories.ObraRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service("obraService")
public class ObraServiceImpl implements ObraService {
    private final ObraRepository obraRepository;
    private final ObraDtoToObra obraDtoToObra;
    private final AutorService autorService;

    public ObraServiceImpl(ObraRepository obraRepository, ObraDtoToObra obraDtoToObra, AutorService autorService) {
        this.obraRepository = obraRepository;
        this.obraDtoToObra = obraDtoToObra;
        this.autorService = autorService;
    }

    @Override
    public Obra create(@Valid ObraDto dto) {
        Obra obra = obraDtoToObra.convert(dto);
        if (obra == null) return null;

        obra.addAutores(autorService.create(dto.getAutores()));
        return obraRepository.save(obra);
    }

}
