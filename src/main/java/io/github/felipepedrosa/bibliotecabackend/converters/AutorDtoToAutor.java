package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AutorDtoToAutor implements Converter<AutorDto, Autor> {
    @Override
    public Autor convert(@Nullable AutorDto source) {
        if (source == null) return null;

        Autor autor = new Autor();
        autor.setId(source.getId());
        autor.setNome(source.getNome());

        return autor;
    }
}
