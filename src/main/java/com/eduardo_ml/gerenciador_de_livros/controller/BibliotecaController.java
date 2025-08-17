package com.eduardo_ml.gerenciador_de_livros.controller;

import com.eduardo_ml.gerenciador_de_livros.dto.BibliotecaRequestDTO;
import com.eduardo_ml.gerenciador_de_livros.dto.BibliotecaResponseDTO;
import com.eduardo_ml.gerenciador_de_livros.service.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaResponseDTO>> listarBibliotecas() {
        return ResponseEntity.ok(bibliotecaService.listarBibliotecas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bibliotecaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BibliotecaResponseDTO> registrarBiblioteca(@Valid @RequestBody BibliotecaRequestDTO bibliotecaDTO) {
        BibliotecaResponseDTO novaBiblioteca = bibliotecaService.registrarBiblioteca(bibliotecaDTO);
        return new ResponseEntity<>(novaBiblioteca, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliotecaResponseDTO> atualizarBiblioteca(@PathVariable Long id, @Valid @RequestBody BibliotecaRequestDTO bibliotecaDTO) {
        BibliotecaResponseDTO bibliotecaAtualizada = bibliotecaService.atualizarBiblioteca(id, bibliotecaDTO);
        return ResponseEntity.ok(bibliotecaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirBiblioteca(@PathVariable Long id) {
        bibliotecaService.excluirBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}