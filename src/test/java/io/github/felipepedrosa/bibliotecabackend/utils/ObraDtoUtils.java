package io.github.felipepedrosa.bibliotecabackend.utils;

import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ObraDtoUtils {

    private ObraDtoUtils() {
    }

    public static Set<ObraDto> createObrasWithoutAutor(int quantity, boolean withId) {
        Set<ObraDto> obras = new HashSet<>(quantity);
        for (int i = 0; i < quantity; i++) {
            obras.add(createObraWithoutAutor(i, withId));
        }

        return obras;
    }

    public static ObraDto createObraWithoutAutor(boolean withId) {
        return createObraWithoutAutor(null, withId);
    }

    private static ObraDto createObraWithoutAutor(Integer referenceNumber, boolean withId) {
        ObraDto obraDto = new ObraDto();
        obraDto.setId(withId ? UUID.randomUUID() : null);
        obraDto.setTitulo(referenceNumber == null ? "Título" : "Título " + 1);
        obraDto.setEditora(referenceNumber == null ? "Editora" : "Editora " + 1);
        obraDto.setUrlImagem("localhos:8080/imgs/teste.png");

        return obraDto;
    }
}
