package io.github.felipepedrosa.bibliotecabackend.services;

import io.github.felipepedrosa.bibliotecabackend.dtos.AutorDto;
import io.github.felipepedrosa.bibliotecabackend.models.Autor;

import java.util.Set;

public interface AutorService {
    Autor create(AutorDto dto);
    Set<Autor> create(Set<AutorDto> dtos);
}
