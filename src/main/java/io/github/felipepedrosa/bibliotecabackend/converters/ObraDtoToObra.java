package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObraDtoToObra implements Converter<ObraDto, Obra> {
    private final AutorDtoToAutor autorDtoToAutor;

    public ObraDtoToObra(AutorDtoToAutor autorDtoToAutor) {
        this.autorDtoToAutor = autorDtoToAutor;
    }

    @Override
    public Obra convert(@Valid @Nullable ObraDto source) {
        if (source == null) return null;

        Obra obra = new Obra();
        obra.setId(source.getId());
        obra.setTitulo(source.getTitulo());
        obra.setEditora(source.getEditora());
        obra.setUrlImagem(source.getUrlImagem());

        if (!source.getAutores().isEmpty()) {
            Set<Autor> autores = source.getAutores().stream()
                    .map(autorDtoToAutor::convert)
                    .collect(Collectors.toSet());

            obra.addAutores(autores);
        }

        return obra;
    }

}
