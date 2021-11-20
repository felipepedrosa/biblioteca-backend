package io.github.felipepedrosa.bibliotecabackend.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Obra implements Serializable {
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false)
    private UUID id;

    @Column(length = 30, nullable = false, unique = true)
    private String titulo;

    @Column(length = 30, nullable = false)
    private String editora;

    @Column(unique = true)
    private String urlImagem;

    @ManyToMany
    @JoinTable(
            name = "obras_autores",
            joinColumns = @JoinColumn(name = "obra_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private final Set<Autor> autores = new HashSet<>();

    public Obra() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void addAutores(Set<Autor> autores) {
        this.getAutores().addAll(autores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obra obra = (Obra) o;
        return titulo.equals(obra.titulo) && editora.equals(obra.editora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, editora);
    }
}
