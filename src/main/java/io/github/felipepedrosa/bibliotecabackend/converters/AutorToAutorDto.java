package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AutorToAutorDto implements Converter<Autor, AutorDto> {
    @Override
    public AutorDto convert(@Nullable Autor source) {
        if (source == null) return null;

        AutorDto autorDto = new AutorDto();
        autorDto.setId(source.getId());
        autorDto.setNome(source.getNome());

        return autorDto;
    }
}
