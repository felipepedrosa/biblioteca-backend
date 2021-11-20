package io.github.felipepedrosa.bibliotecabackend.repositories;

import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ObraRepository extends PagingAndSortingRepository<Obra, UUID> {
    boolean existsByTituloIgnoreCase(String titulo);
}
