package io.github.felipepedrosa.bibliotecabackend.services;

import io.github.felipepedrosa.bibliotecabackend.converters.AutorDtoToAutor;
import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import io.github.felipepedrosa.bibliotecabackend.repositories.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorDtoToAutor autorDtoToAutor;
    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorDtoToAutor autorDtoToAutor, AutorRepository autorRepository) {
        this.autorDtoToAutor = autorDtoToAutor;
        this.autorRepository = autorRepository;
    }

    private Optional<Autor> verifyIfExistsByName(String name) {
        return autorRepository.findByNomeIgnoreCase(name);
    }


    @Override
    public Autor create(AutorDto dto) {
        Autor autor = autorDtoToAutor.convert(dto);
        if (autor == null) return null;

        Optional<Autor> autorOptional = verifyIfExistsByName(autor.getNome());
        return autorOptional.orElseGet(() -> autorRepository.save(autor));
    }


    @Override
    public Set<Autor> create(Set<AutorDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return null;

        HashSet<Autor> autores = new HashSet<>(dtos.size());

        for (AutorDto dto : dtos) {
            Autor autor = create(dto);
            autores.add(autor);
        }

        return autores;
    }

}
