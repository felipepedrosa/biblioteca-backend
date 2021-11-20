package io.github.felipepedrosa.bibliotecabackend.controllers;

import io.github.felipepedrosa.bibliotecabackend.dtos.ObraDto;
import io.github.felipepedrosa.bibliotecabackend.models.Obra;
import io.github.felipepedrosa.bibliotecabackend.services.ObraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/obras")
public class ObraController {
    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @PostMapping
    public ResponseEntity<ObraDto> create(@Valid @RequestBody ObraDto dto) {
        Obra obra = obraService.create(dto);
        return ResponseEntity.created(URI.create("/obras/" + obra.getId())).build();
    }

}
