package io.github.felipepedrosa.bibliotecabackend.utils;

import io.github.felipepedrosa.bibliotecabackend.models.Obra;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ObraUtils {
    private ObraUtils() {
    }

    public static Set<Obra> createObrasWithoutAutor(int quantity) {
        Set<Obra> obras = new HashSet<>(quantity);
        for (int i = 0; i < quantity; i++) {
            obras.add(createObraWithoutAutor(i));
        }

        return obras;
    }

    public static Obra createObraWithoutAutor() {
        return createObraWithoutAutor(null);
    }

    private static Obra createObraWithoutAutor(Integer referenceNumber) {
        Obra obra = new Obra();
        obra.setId(UUID.randomUUID());
        obra.setTitulo(referenceNumber == null ? "Título" : "Título " + 1);
        obra.setEditora(referenceNumber == null ? "Editora" : "Editora " + 1);
        obra.setUrlImagem("localhos:8080/imgs/teste.png");

        return obra;
    }
}
