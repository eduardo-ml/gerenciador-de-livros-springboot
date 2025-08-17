package com.eduardo_ml.gerenciador_de_livros.controller;

import com.eduardo_ml.gerenciador_de_livros.dto.AutorRequestDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.AutorResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listarAutores() {
        return ResponseEntity.ok(autorService.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> registrarAutor(@Valid @RequestBody AutorRequestDTO autorDTO) {
        AutorResponseDTO novoAutor = autorService.registrarAutor(autorDTO);
        return new ResponseEntity<>(novoAutor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizarAutor(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO autorDTO) {
        AutorResponseDTO autorAtualizado = autorService.atualizarAutor(id, autorDTO);
        return ResponseEntity.ok(autorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        autorService.excluirAutor(id);
        return ResponseEntity.noContent().build();
    }
}