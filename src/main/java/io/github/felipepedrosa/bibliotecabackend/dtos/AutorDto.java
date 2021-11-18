package io.github.felipepedrosa.bibliotecabackend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class AutorDto implements Serializable {
    @Null
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 30)
    private String nome;


    public AutorDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorDto autorDto = (AutorDto) o;
        return Objects.equals(nome, autorDto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
