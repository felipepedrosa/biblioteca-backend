package io.github.felipepedrosa.bibliotecabackend.utils;

import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AutorUtils {
    private AutorUtils() {
    }

    public static Autor createAutor() {
        return CollectionUtils.firstElement(createAutors(1));
    }

    public static Set<Autor> createAutors(int quantity) {
        Set<Autor> autores = new HashSet<>(quantity);

        for (int i = 0; i < quantity; i++) {
            Autor autor = new Autor();
            autor.setId(UUID.randomUUID());
            autor.setNome("Autor " + i);

            autores.add(autor);
        }

        return autores;
    }

}
