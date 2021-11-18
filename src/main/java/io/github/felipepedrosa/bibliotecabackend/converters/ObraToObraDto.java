package io.github.felipepedrosa.bibliotecabackend.converters;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObraToObraDto implements Converter<Obra, ObraDto> {
    private final AutorToAutorDto autorToAutorDto;

    public ObraToObraDto(AutorToAutorDto autorToAutorDto) {
        this.autorToAutorDto = autorToAutorDto;
    }

    @Override
    public ObraDto convert(@Nullable Obra source) {
        if (source == null) return null;

        ObraDto dto = new ObraDto();
        dto.setId(source.getId());
        dto.setTitulo(source.getTitulo());
        dto.setEditora(source.getEditora());
        dto.setUrlImagem(source.getUrlImagem());

        if (!source.getAutores().isEmpty()) {
            Set<AutorDto> autores = source.getAutores().stream()
                    .map(autorToAutorDto::convert)
                    .collect(Collectors.toSet());

            dto.setAutores(autores);
        }

        return dto;
    }

}
