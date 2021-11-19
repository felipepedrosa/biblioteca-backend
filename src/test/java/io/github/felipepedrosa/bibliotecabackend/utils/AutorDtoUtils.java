package io.github.felipepedrosa.bibliotecabackend.utils;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AutorDtoUtils {
    private AutorDtoUtils() {
    }

    public static AutorDto createAutor() {
        return CollectionUtils.firstElement(createAutors(1));
    }

    public static Set<AutorDto> createAutors(int quantity) {
        Set<AutorDto> autores = new HashSet<>(quantity);

        for (int i = 0; i < quantity; i++) {
            AutorDto autorDto = new AutorDto();
            autorDto.setId(UUID.randomUUID());
            autorDto.setNome("Autor " + i);

            autores.add(autorDto);
        }

        return autores;
    }
}
