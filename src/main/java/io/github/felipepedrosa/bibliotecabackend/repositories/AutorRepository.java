package io.github.felipepedrosa.bibliotecabackend.repositories;

import io.github.felipepedrosa.bibliotecabackend.models.Autor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends CrudRepository<Autor, UUID> {
    Optional<Autor> findByNomeIgnoreCase(String nome);
}
