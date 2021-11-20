package io.github.felipepedrosa.bibliotecabackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Autor implements Serializable {
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false)
    private UUID id;

    @Column(length = 30, nullable = false, unique = true)
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "autores")
    private final Set<Obra> obras = new HashSet<>();

    public Autor() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Obra> getObras() {
        return obras;
    }

    public void addObras(Set<Obra> obras) {
        this.obras.addAll(obras);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return nome.equals(autor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
