package io.github.felipepedrosa.bibliotecabackend.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ObraDto implements Serializable {
    @Null
    private UUID id;

    @Size(min = 1, max = 30)
    @NotBlank
    private String titulo;

    @Size(min = 1, max = 30)
    @NotBlank
    private String editora;

    @Size(max = 255)
    private String urlImagem;

    @Valid
    @NotEmpty
    private Set<AutorDto> autores;

    public ObraDto() {
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

    public Set<AutorDto> getAutores() {
        return autores;
    }

    public void setAutores(Set<AutorDto> autores) {
        this.autores = autores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObraDto obraDto = (ObraDto) o;
        return Objects.equals(titulo, obraDto.titulo) && Objects.equals(editora, obraDto.editora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, editora);
    }
}
